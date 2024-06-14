package TestCase1.test10;

public class MainClass {
    public static final String PLAYER_2 = "2";
    public static final String PLAYER_1 = "1";
    public static final String ERROR = "-1";
    public static final String FAIL = "失败";
    public static final String SUCCESS = "成功";
    public static final String PING = "平局";
    public static final String BU = "布";
    public static final String JIAN = "剪刀";
    public static final String SHI = "石头";

    public static void main(String[] args) {
        // 平局测试
        doTest(SHI, SHI, PING);
        doTest(JIAN, JIAN, PING);
        doTest(BU, BU, PING);

        // PLAYER_1 胜利测试
        doTest(SHI, JIAN, PLAYER_1);
        doTest(JIAN, BU, PLAYER_1);
        doTest(BU, SHI, PLAYER_1);

        // PLAYER_2 胜利测试
        doTest(SHI, BU, PLAYER_2);
        doTest(JIAN, SHI, PLAYER_2);
        doTest(BU, JIAN, PLAYER_2);

        // 非法输入测试
        doTest("abc", "xyz", ERROR);
        doTest(SHI, "xyz", ERROR);
        doTest("abc", JIAN, ERROR);

        // 边界测试：合法与非法的组合
        doTest(SHI, "123", ERROR);
        doTest("!@#", JIAN, ERROR);
    }

    private static void doTest(String a, String b, String expected) {
        if (expected.equals(doCai(a, b)))
            System.out.println(SUCCESS);
        else
            System.out.println(FAIL);
    }

    public static String doCai(String a, String b) {
        if (!isValid(a) || !isValid(b))
            return ERROR;

        if (a.equals(b))
            return PING;

        String result;
        switch (a) {
            case SHI:
                result = b.equals(JIAN) ? PLAYER_1 : PLAYER_2;
                break;
            case JIAN:
                result = b.equals(BU) ? PLAYER_1 : PLAYER_2;
                break;
            case BU:
                result = b.equals(SHI) ? PLAYER_1 : PLAYER_2;
                break;
            default:
                result = null;
        }
        return result;
    }

    public static boolean isValid(String val) {
        return SHI.equals(val) || JIAN.equals(val) || BU.equals(val);
    }
}
