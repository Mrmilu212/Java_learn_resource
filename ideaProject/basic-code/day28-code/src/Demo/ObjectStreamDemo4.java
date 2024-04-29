package Demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ObjectStreamDemo4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
        一次读入多个对象
         */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("day28-code\\a.txt"));
        ArrayList<Student> list = (ArrayList<Student>) ois.readObject();

        for (Student student : list) {
            System.out.println(student);
        }
    }
}
