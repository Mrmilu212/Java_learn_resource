package weind;

import java.util.Scanner;

public class GetRectangleArea {
    public static void main(String[] args) {
        getRectangleArea(0,0);
    }
    public static float getRectangleArea(float i, float j) {
        Scanner input = new Scanner(System.in);
        float area = 0;
        System.out.println("请输入长：");
         i = input.nextFloat();
        System.out.println("请输入宽：");
         j = input.nextFloat();

        if (i < 0 || j < 0) {
            System.out.println("输入值小于零");
            area = -1;
        } else {
            area = getArea(i, j);
        }
        System.out.println("The ared is " + area);
        return area;
    }

    public static float getArea(float x, float y) {
        float temp = x * y;
        return temp;
    }
}