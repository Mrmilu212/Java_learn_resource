package TestCase1.Test6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    Config config;

    @BeforeEach
    void setUp() {
         config = new Config();
    }

    @AfterEach
    void tearDown() {
        config = null;
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testWindows() {
        assertEquals("C:\\test.ini", config.getConfigFile("test.ini"));
    }
    @Test
    @EnabledOnOs({ OS.LINUX, OS.MAC })
    void testLinuxAndMac() {
        assertEquals("/usr/local/test.cfg", config.getConfigFile("test.cfg"));
    }


}