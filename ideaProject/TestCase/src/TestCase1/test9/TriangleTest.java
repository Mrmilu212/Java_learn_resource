package TestCase1.test9;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    // 测试等边三角形
    @Test
    public void testEquilateralTriangle() {
        Triangle tri = new Triangle(3, 3, 3);
        assertTrue(tri.isEquilateral());
        assertTrue(tri.isIsosceles());
        assertFalse(tri.isScalene());
    }

    // 测试等腰三角形
    @Test
    public void testIsoscelesTriangle() {
        // 边界条件：等腰三角形，两边相等
        Triangle tri = new Triangle(3, 3, 4);
        assertTrue(tri.isIsosceles());
        assertTrue(tri.isScalene());

        // 边界条件：等腰三角形，底边相等
        tri = new Triangle(3, 4, 3);
        assertTrue(tri.isIsosceles());
        assertTrue(tri.isScalene());

        // 边界条件：等腰三角形，两边相等
        tri = new Triangle(3, 4, 3);
        assertTrue(tri.isIsosceles());
        assertTrue(tri.isScalene());
    }

    // 测试普通三角形
    @Test
    public void testScaleneTriangle() {
        // 边界条件：普通三角形，三边不等
        Triangle tri = new Triangle(3, 4, 5);
        assertFalse(tri.isIsosceles());
        assertTrue(tri.isScalene());
    }
}