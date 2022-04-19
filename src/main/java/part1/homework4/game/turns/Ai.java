package part1.homework4.game.turns;


import part1.homework4.consts.ConstsClass;
import part1.homework4.consts.UtilsClass;
import part1.homework4.field.Coordinate;
import part1.homework4.field.consts.DotsClass;
import part1.homework4.field.consts.FieldDefault;
import part1.homework4.game.GameClass;

public class Ai {
    GameClass game;
    public Ai (GameClass game){
        this.game = game;
    }

    public class AiPotentialTurn {

        public int myPower = 1;
        public int fullPotentional = 0;
        //Нужно для обработки более сложных кейсов при >3
        public int initial_potential;
        public int reverse_potentional;

        private Coordinate start_coord;
        private int direction;
        public int winCount;
        public int totalPower = myPower; //Значение нужно чтобы потом не складывать
        //Оно равняется тому сколько клеток сейчас в объединении + потенциал

        public Coordinate potential_move = null;
        private Coordinate move_coord;
        public Coordinate priority_move = null; //Переменная нужна, поскольку мы обходим много циклом
        //Держать ее в памяти на случай если нам нужно будет закрыть дырку (пример x . x)

        public AiPotentialTurn(Coordinate _start_coordinate, int _direction, char myDot) {
            start_coord = _start_coordinate;
            direction = _direction;

            //Получаем координаты для похода
            move_coord = Coordinate.getCoordinatesMoveForDirection(direction);

            //Просчитываем движения вперед и назад
            calculateMoves(false, myDot);
            calculateMoves(true, myDot);

            //Если мы нашли приоритетный ход пока это только объединение
            //То заместим любые ходы на него
            if (priority_move != null)
                potential_move = priority_move;

            totalPower = myPower + fullPotentional;
        }

        //Параметр отвечает за то в какую сторону мы будем двигаться, вперед или назад
        //по клеткам
        public void calculateMoves(boolean Revers, char MyDot) {
            int new_x = start_coord.x;
            int new_y = start_coord.y;
            char next_value;
            char next_next_value;

            //Определяем порядок движения
            int x_move, y_move;

            if (Revers) {
                x_move = (move_coord.x == 0) ? 0 : move_coord.x * -1;
                y_move = (move_coord.y == 0) ? 0 : move_coord.y * -1;
            } else {
                x_move = move_coord.x;
                y_move = move_coord.y;
            }

            //Считаем поход в выбранном направлении
            while (true) {
                //Получили новую координату
                new_x = new_x + x_move;
                new_y = new_y + y_move;

                next_value = game.field.nextPointValue(new Coordinate(new_x, new_y));

                if (next_value == DotsClass.DOT_EMPTY) {
                    fullPotentional++;

                    if (Revers)
                        reverse_potentional++;
                    else
                        initial_potential++;

                    //region Проверка на необходимость объединения
                    Coordinate ch_prior_coord = new Coordinate(new_x + x_move, new_y + y_move);
                    next_next_value = game.field.nextPointValue(ch_prior_coord);
                    if (next_next_value == MyDot) {
                        myPower++; //Увеличим также силу хоть сейчас позиции и разделены это нужно для увеличения
                        //Потенциала хода  возможен конечно вариант типа хх _ хх когда мне бы следовало и дальше пойти
                        priority_move = new Coordinate(new_x, new_y); //Приравняем значение и теперь это приоритетные координаты
                    } else if (next_next_value == DotsClass.DOT_EMPTY) {
                        //В этом случае не будем считать движение приоритетным, поскольку мы до него доберемся и дальше
                        //А так будем двигаться последовательно

                    }
                    //Проверим предыдущие значение, и не будем переступать через сектор
                    if (potential_move == null)
                        potential_move = new Coordinate(new_x, new_y);
                    //endregion
                } else if (next_value == MyDot) {
                    myPower++;

                    //Проверим следующую клетку, и если она подходит для хода то учтем это
                    Coordinate ch_prior_coord = new Coordinate(new_x + x_move, new_y + y_move);
                    next_next_value = game.field.nextPointValue(ch_prior_coord);
                    if (next_next_value == DotsClass.DOT_EMPTY) {
                        potential_move = ch_prior_coord;
                    }
                } else
                    break;
            }
        }

    }

    public void aiTurn() {

        //1. Получаем лучший предрасчитаный ход AI
        AiPotentialTurn bestAiTurn = getBestTurn(DotsClass.aiMark);
        //2. Получим предрасчитанный лучший ход для человека(также действует рандом) на случай одинаковых ходов
        AiPotentialTurn bestHumanPrecalculationTurn = getBestTurn(DotsClass.playerMark);

        //3. Выбираем наше поведение
        if (bestHumanPrecalculationTurn == null) {
            //Если нет лучшего хода у человека. то действуем по своему желанию
            //Или случайно если нет нашего или по тому что ранее определили
            if (bestAiTurn == null)
                chooseBestEmptyTurn();
            else
                game.field.fillCell(bestAiTurn.potential_move, DotsClass.aiMark);
        } else {
            //Если у нас нет лучшего хода то точно блокируем человека
            if (bestAiTurn == null)
                game.field.fillCell(bestHumanPrecalculationTurn.potential_move, DotsClass.aiMark);
            else {
                //Не обращаем внимание на человека, поскольку мы сейчас выиграем
                if (isCloseToWin(bestAiTurn))
                    game.field.fillCell(bestAiTurn.potential_move, DotsClass.aiMark);
                    //Если компьютер в похожем положении то мы должны его блокировать сразу
                else if (isCloseToWin(bestHumanPrecalculationTurn))
                    game.field.fillCell(bestHumanPrecalculationTurn.potential_move, DotsClass.aiMark);
                    //Если потенциально нам осталось два хода до завершения
                else if (isGuaranteedVictoryReversal(bestAiTurn))
                    game.field.fillCell(bestAiTurn.potential_move, DotsClass.aiMark);
                else if (isGuaranteedVictoryReversal(bestHumanPrecalculationTurn))
                    game.field.fillCell(bestHumanPrecalculationTurn.potential_move, DotsClass.aiMark);
                else if (bestAiTurn.priority_move != null) //Если у нас есть возможность перекрыть перекрываем
                    game.field.fillCell(bestAiTurn.priority_move, DotsClass.aiMark);
                else if (bestHumanPrecalculationTurn.priority_move != null)
                    game.field.fillCell(bestHumanPrecalculationTurn.priority_move, DotsClass.aiMark);
                else
                    game.field.fillCell(bestAiTurn.potential_move, DotsClass.aiMark);
            }
        }
    }

    private AiPotentialTurn getBestTurn(char Mark) {
        int[] rndDirections = FieldDefault.Directions;

        Coordinate[] myTurns = game.field.getArrayOfDotTurns(Mark);
        AiPotentialTurn potentialTurn = null; //Зафиксируем лучший ход из возможных наших
        //Для каждой точки которая у нас поставлена и по каждому из возможных направлений подбираем лучший ход проходя
        //весь цикл по каждому направлению, конечно же будут дубли которые имеют одинаковую текущую мощь и потенциал
        for (Coordinate myTurn : myTurns) {
            //В боевом режиме будем выдавать случайные походы
            if (!ConstsClass.debug) {
                UtilsClass.ShuffleIntArray(rndDirections);
            }

            for (int direction : rndDirections) {
                AiPotentialTurn nextTurn = new AiPotentialTurn(myTurn, direction, Mark);
                //Когда точка окружена бесполезно что то делать
                if (nextTurn.fullPotentional == 0)
                    continue;

                //Когда даже если сделать все ходы то потенциала не хватит на победу
                //Считаем ходы бесполезными
                if (nextTurn.totalPower < nextTurn.winCount)
                    continue;

                //Заполняем потенциальный ход, и потом его только пербьем если найдется получше
                if (potentialTurn == null) {
                    potentialTurn = nextTurn;
                } else {
                    //если уже ранее был ход то сравниваем приоритетнее для нас
                    //это текщие способности чем потенциальный рост
                    if (nextTurn.myPower > potentialTurn.myPower) {
                        potentialTurn = nextTurn;
                    } else if (nextTurn.myPower == potentialTurn.myPower) {
                        //Когда текущие силы равны, а потенциала больше, перед тот вариант
                        //В качестве более предпочтительного
                        if (nextTurn.totalPower > potentialTurn.totalPower)
                            potentialTurn = nextTurn;
                        else {
                            //Вводим чуть чуть рандома по выбору шага,
                            //И да я понимаю что у 1 - 50% у второго 25% и далее такой же шанс
                            //Иначе мне нужно их соранять и потом выбирать нужный
                            if (UtilsClass.random.nextInt(2) > 0)
                                potentialTurn = nextTurn;
                        }

                    }
                }

            }
        }
        return potentialTurn;
    }


    private boolean isCloseToWin(AiPotentialTurn turn) {
        return ((turn.myPower + 1) >= game.winCountInALine) &&
                (turn.fullPotentional > 0);
    }

    private boolean isGuaranteedVictoryReversal(AiPotentialTurn turn) {
        //Даже не рассматриваем ситуацию поскольку окружения быть не может
        if (turn.initial_potential == 0 || turn.reverse_potentional == 0)
            return false;

        //Нет гарантированный победы, поскольку нужно поставить больше 2-х элементов
        if ((game.winCountInALine - 2) < turn.myPower)
            return false;

        //Один элемент тоже нет смысла окружать а то скучно
        return turn.myPower != 1;
    }

    private void RandomAiTurn() {
        int x;
        int y;
        Coordinate new_coordinate;

        do {
            x = UtilsClass.random.nextInt(game.field.size_field);
            y = UtilsClass.random.nextInt(game.field.size_field);
            new_coordinate = new Coordinate(x, y);
        } while (!game.field.isCellEmpty(new_coordinate));

        game.field.fillCell(new_coordinate, DotsClass.aiMark);
    }

    private void chooseBestEmptyTurn() {

        pointPotential bestPotential = null;

        for (int x = 0; x < game.field.size_field ; x++) {
            for (int y = 0; y < game.field.size_field ; y++) {
                Coordinate curCoord  = new Coordinate(x,y);
                if (game.field.cellValue(curCoord) == DotsClass.DOT_EMPTY){
                    pointPotential point = new pointPotential(new Coordinate(x,y));

                    if (bestPotential == null)
                        bestPotential = point;
                    else
                    {
                        //Если в новой точке больше линий, или при прочих равных возможных кликов
                        //Или же рандом по замене
                        if (point.potentionalLines > bestPotential.potentionalLines)
                            bestPotential = point;
                        else if ((point.potentionalLines == bestPotential.potentionalLines)
                                && (point.potentionalPoints > bestPotential.potentionalPoints))
                            bestPotential = point;
                    }
                }
            }
        }
        //Если какой то сбой то ходим случайно
        if (bestPotential == null)
            RandomAiTurn();
        else
            game.field.fillCell(bestPotential.start_coord, DotsClass.aiMark);
    }

    private class pointPotential {
        public Coordinate start_coord;
        public int potentionalPoints = 1;
        public int potentionalLines = 0;
        int curLine;

        public pointPotential(Coordinate start_coord) {
            this.start_coord = start_coord;

            //Обходим точки и ищем потенциально с наибольшим количеством вариантов
            //Также действуем по направленияем
            for (int direction : FieldDefault.Directions) {
                Coordinate move_coord = Coordinate.getCoordinatesMoveForDirection(direction);
                curLine = 1;
                calculatePotentionals(false, move_coord);
                calculatePotentionals(true, move_coord);
            }
        }

        public void calculatePotentionals(boolean Revers, Coordinate move_coord) {
            int new_x = start_coord.x;
            int new_y = start_coord.y;
            char next_value;

            //Определяем порядок движения
            int x_move, y_move;

            if (Revers) {
                x_move = (move_coord.x == 0) ? 0 : move_coord.x * -1;
                y_move = (move_coord.y == 0) ? 0 : move_coord.y * -1;
            } else {
                x_move = move_coord.x;
                y_move = move_coord.y;
            }

            //Считаем поход в выбранном направлении
            while (true) {
                //Получили новую координату
                new_x = new_x + x_move;
                new_y = new_y + y_move;

                if (curLine >= game.winCountInALine)
                {
                    potentionalLines ++;
                    curLine = 1;//Вдруг возможно в обе стороны
                }

                next_value = game.field.nextPointValue(new Coordinate(new_x, new_y));

                //Пустая клетка или клетка АИ подходят
                if (next_value == DotsClass.DOT_EMPTY) {
                    potentionalPoints++;
                    curLine = curLine+1;
                }
                else if (next_value == DotsClass.aiMark)
                {
                    potentionalPoints++;
                    curLine = curLine+1;
                }
                else
                    break;
            }

        }

    }
}
