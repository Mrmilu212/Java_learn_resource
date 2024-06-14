package TestCase1.Test2;

public class BMITest {
    // 根据BMI值判断健康状况
    public String getBMIType(double weight,double height){
        double bmi = 0.0;
        String result = "";
        if( weight>0 && height>0){
            //1.计算bmi
            bmi = weight/(height*height);
            System.out.println(bmi);
            //2.根据bmi判断所属健康分类
            if(bmi<18.5){
                result = "偏瘦";
            }else if(bmi<24){
                result = "正常";
            }else if(bmi<28){
                result = "偏胖";
            }else{
                result = "肥胖";
            }
        }else{
            return "Weight or height error!";
        }
        return result;
    }
}
