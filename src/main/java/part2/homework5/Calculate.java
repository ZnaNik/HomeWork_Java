package part2.homework5;

public class Calculate {
    public static float strange_formula(float value, int index){
        return (float)(value * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
    }
}
