package TestCase1.Test2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BMITestTest {
    BMITest bmiTest;
    //使用Fixture
    @BeforeEach
    void setUp() {
        bmiTest = new BMITest();
    }

    @AfterEach
    void tearDown() {
        bmiTest = null;
    }
    //异常数据测试
    @Test
    void testInvalidInput() {
        assertEquals("Weight or height error!", bmiTest.getBMIType(-1, 1.75), "Should return error for negative weight");
        assertEquals("Weight or height error!", bmiTest.getBMIType(70, 0), "Should return error for zero height");
        assertEquals("Weight or height error!", bmiTest.getBMIType(0, 1.75), "Should return error for zero weight");
    }
    //条件测试

    //参数化测试
    @ParameterizedTest
    @CsvSource({ "60,1.65,正常","60,1.70,正常","60,1.90,偏瘦","60,1.50,偏胖"})
    void testBMIType(String input1,String input2, String result) {
        assertEquals(result, bmiTest.getBMIType(Double.parseDouble(input1),Double.parseDouble(input2)));
    }
}