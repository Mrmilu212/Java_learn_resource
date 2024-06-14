package TestCase1.Test8;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BloodPressureTest {
    BloodPressure bloodPressure;
    @BeforeEach
    void setUp() {
        bloodPressure = new BloodPressure();
    }

    @AfterEach
    void tearDown() {
        bloodPressure = null;
    }



    @ParameterizedTest
    @CsvSource({ "100,70,正常","130,85,正常高值","150,95,1级高血压","170,105,2级高血压"})
    void testGetPressureLevel(String input1,String input2, String result) {
        assertEquals(result,bloodPressure.getPressureLevel(Integer.parseInt(input1),Integer.parseInt(input2)));
    }
}