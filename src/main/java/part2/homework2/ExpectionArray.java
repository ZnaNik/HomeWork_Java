package part2.homework2;

public class ExpectionArray {

    private static final int Array_Size = 4;

    public void transform(String [][] arr)
            throws MyArraySizeException, MyArrayDataException, NullPointerException{

        if (arr == null) throw new NullPointerException("Null not acceptable");

        if (arr.length != Array_Size)
            throw new MyArraySizeException("Array size not equal " + Array_Size);

        //Придется проверять в цикле наш массив, потому что внутри массива может быть такой же размер, или
        //на любой точке
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            String[] sub_array = arr[i];

            if (sub_array.length != Array_Size)
                throw new MyArraySizeException("Sub array in line: " + (i+1) + " is not equal to: " + Array_Size);

            //Теперь циклом делаем суммированием
            for (int j = 0; j < sub_array.length; j++) {
                if (!isNumeric(arr[i][j]))
                    throw new MyArrayDataException("in line : " + (i+1) + " " + (j+1)+ " is not int");

                try{
                    sum = sum + Integer.parseInt(arr[i][j]);
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                    throw new MyArrayDataException("in line : " + (i+1) + " " + (j+1)+ " cant parse int ");
                }
            }

        }
        System.out.println("Sum: " + sum);
    }
    private boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
}

