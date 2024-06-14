package TestCase1.Test5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fact() {
    }

    @Test
    void testNegative() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.fact(-1));
    }


}