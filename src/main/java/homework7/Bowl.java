package homework7;

public class Bowl {

    //расчет ведется в граммах
    private int curFood;
    private int foodLimit;

    public Bowl(int foodLimit) {
        this.foodLimit = foodLimit;
    }

    public boolean putFood(int foodInGrams) {

        if (foodInGrams + curFood > foodLimit) {
            System.out.printf("Вы пытаетесь положить в миску, больше еды чем она может уместить лимит: %s\n", (foodLimit));
            return false;
        }
        curFood = curFood + foodInGrams;
        System.out.println("В тарелку положили еды теперь там: " + curFood);

        return true;
    }

    //для задачи фактически не нужно оставил так по логике
    public int getCurFood(){
        return curFood;
    }

    public boolean eatFood(Animal animal){
        if (animal.appetite > curFood)
            return false;
        else
        {
            // 0 победится здесь
            curFood = curFood - animal.appetite;
            return true;
        }

    }
}
