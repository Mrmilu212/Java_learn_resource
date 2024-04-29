package Demo;

import java.io.Serial;
import java.io.Serializable;

//Serializable 是一个标志性接口，里面没有要是实现的方法
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = -5897497645157113172L;
    private String name;
    private int age;

    private String address;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Student{name = " + name + ", age = " + age + "}";
    }
}
