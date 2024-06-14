package TestCase1.test10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainClassTest {
    @Test
    public void testDoCai() {
        assertEquals(MainClass.PING, MainClass.doCai(MainClass.SHI, MainClass.SHI));
        assertEquals(MainClass.PING, MainClass.doCai(MainClass.JIAN, MainClass.JIAN));
        assertEquals(MainClass.PING, MainClass.doCai(MainClass.BU, MainClass.BU));
        assertEquals(MainClass.PLAYER_1, MainClass.doCai(MainClass.SHI, MainClass.JIAN));
        assertEquals(MainClass.PLAYER_2, MainClass.doCai(MainClass.SHI, MainClass.BU));
        assertEquals(MainClass.PLAYER_2, MainClass.doCai(MainClass.JIAN, MainClass.SHI));
        assertEquals(MainClass.PLAYER_1, MainClass.doCai(MainClass.JIAN, MainClass.BU));
        assertEquals(MainClass.PLAYER_1, MainClass.doCai(MainClass.BU, MainClass.SHI));
        assertEquals(MainClass.PLAYER_2, MainClass.doCai(MainClass.BU, MainClass.JIAN));
        assertEquals(MainClass.ERROR, MainClass.doCai("abc", "xyz"));
    }
}