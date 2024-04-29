package Test.myioTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
    public static void main(String[] args) throws IOException {
        /*
        在网站上爬取数据，并进行拼接

        百家姓：https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d
        男生姓名：https://baike.pcbaby.com.cn/qzbd/180747.html
        女生姓名：https://baijiahao.baidu.com/s?id=1688033594862384923
         */
        //资源网址
        String familyName = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d";
        String boyName = "https://www.zhihu.com/question/456369981";
        String girlName = "https://baijiahao.baidu.com/s?id=1688033594862384923";

        //获取内容
        String fName = webCrawler(familyName);
        String bName = webCrawler(boyName);
        String gName = webCrawler(girlName);

        //初步数据过滤
        ArrayList<String> FNTempList = getData(fName, "(.{4})(，|。)", 1);
        ArrayList<String> BNTempList = getData(bName, "(\s([\u4E00-\u9FA5]){2}){2}", 0);
        ArrayList<String> GNTempList = getData(gName, "(([\u4E00-\u9FA5]{2})(、)){6}", 0);


        //进一步数据过滤
        ArrayList<String> finalFamilyName = DataMining(FNTempList.toString(), "[\u4E00-\u9FA5]", 0);
        ArrayList<String> finalBoyName = DataMining(BNTempList.toString(), "[\u4E00-\u9FA5]{2}", 0);
        ArrayList<String> finalGirlName = DataMining(GNTempList.toString(), "[\u4E00-\u9FA5]{2}", 0);

        //获取数据
        getInfos(finalFamilyName, finalBoyName, finalGirlName, 50, 80);


    }

    private static ArrayList<String> getInfos(ArrayList<String> FamilyName, ArrayList<String> BoyName, ArrayList<String> GirlName, int boyCount, int girlCount) throws IOException {
        //创建HashSet集合，暂存男生姓名
        HashSet<String> boyHs = new HashSet<>();
        while (true) {
            if (boyHs.size() == boyCount) {
                break;
            }
            //打乱数据
            Collections.shuffle(FamilyName);
            Collections.shuffle(BoyName);
            //拼接并添加元素
            boyHs.add(FamilyName.get(0) + BoyName.get(0));
        }

        //创建HashSet集合，暂存女生姓名
        HashSet<String> girlHs = new HashSet<>();
        while (true) {
            if (girlHs.size() == girlCount) {
                break;
            }
            //打乱数据
            Collections.shuffle(FamilyName);
            Collections.shuffle(GirlName);
            //拼接并添加元素
            girlHs.add(FamilyName.get(0) + GirlName.get(0));
        }

        //拼接性别和年龄
        //创建ArrayList集合，保存最终数据
        ArrayList<String> list = new ArrayList<>();
        Random r = new Random();
        //拼接男生信息
        for (String name : boyHs) {
            int age = r.nextInt(10) + 18;
            list.add(name + "-男-" + age);
        }

        for (String name : girlHs) {
            int age = r.nextInt(8) + 18;
            list.add(name + "-女-" + age);
        }

        //将数据添加到本地文件中
        BufferedWriter bw = new BufferedWriter(new FileWriter("day28-code\\name.txt"));
        for (String s : list) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();

        return list;
    }

    //根据要求获取相应的数据
    private static ArrayList<String> DataMining(String str, String regex, int index) {
        //创建集合
        ArrayList<String> list = new ArrayList<>();
        //创建pattern对象
        Pattern pattern = Pattern.compile(regex);
        //获取正则表达式对象
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            //获取符合正则表达式的第index个分组
            if (!list.contains(matcher.group(index)))
                list.add(matcher.group(index));
        }
        return list;
    }

    //对爬取的数据进行初步处理
    private static ArrayList<String> getData(String str, String regex, int index) {
        //创建集合
        ArrayList<String> list = new ArrayList<>();
        //创建pattern对象
        Pattern pattern = Pattern.compile(regex);
        //获取正则表达式对象
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            //获取符合正则表达式的第index个分组
            list.add(matcher.group(index));
        }
        return list;
    }


    /*
     * 作用：从网络中爬取数据，把数据拼接成字符串返回
     *
     * */
    private static String webCrawler(String str) throws IOException {
        //定义StringBuilder对象
        StringBuilder sb = new StringBuilder();
        //创建URL对象
        URL url = new URL(str);
        //连接上这个网址
        //细节：保证网络是连通的，而且这个网站是可以连接上的
        URLConnection conn = url.openConnection();
        //读取数据
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
        int ch;
        while ((ch = isr.read()) != -1) {
            sb.append((char) ch);
        }
        return sb.toString();
    }
}
