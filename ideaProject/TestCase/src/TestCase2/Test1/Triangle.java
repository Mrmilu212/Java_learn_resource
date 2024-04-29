package TestCase2.Test1;

public class Triangle {


    public String triangle(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return "not a trangle";
        } else {
            int max = a;
            int min = b + c;
            if (b > max) {
                max = b;
                min = a + c;
            }
            if (c > max) {
                max = c;
                min = a + b;
            }
            if (min <= max) {// 最小两边之和不大于第三边
                return "not a triangle";
            }
            if (a == b || b == c || a == c) {
                if (a == b && b == c) {
                    return "equilateral";//等边三角形
                } else {
                    return "isosceles";//等腰三角形
                }
            } else {
                return "scalene";//普通三角形
            }
        }
    }


}
