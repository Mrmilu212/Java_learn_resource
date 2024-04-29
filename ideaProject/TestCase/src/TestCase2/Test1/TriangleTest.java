package TestCase2.Test1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    Triangle triangle;

    @BeforeEach
    void setUp() {
        triangle = new Triangle();
    }

    @AfterEach
    void tearDown() {
        triangle = null;
    }

    //参数化测试需要用到标签@ParameterizedTest
    @ParameterizedTest
    @CsvSource({"2,2,2,equilateral","3,3,2,isosceles","2,3,3,isosceles","3,4,5,scalene",
            "1,2,3,not a triangle","3,1,2,not a triangle","2,3,1,not a triangle"})
    void testTriangle(String input1,String input2,String input3,String result) {
            assertEquals(result,triangle.triangle(Integer.parseInt(input1),Integer.parseInt(input2),Integer.parseInt(input3)));
    }
}