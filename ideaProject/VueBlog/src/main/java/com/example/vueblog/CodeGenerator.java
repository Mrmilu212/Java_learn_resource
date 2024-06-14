package com.example.vueblog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import java.util.*;

/**
 * 代码生成器
 */
public class CodeGenerator {
    public static void main(String[] args) {
        getCode();
    }

    public static void getCode() {
        Scanner scanner = new Scanner(System.in);

        // 获取表名
        System.out.println("请输入要生成代码的表名，多个表名请用逗号分隔：");
        String tableInput = scanner.nextLine();
        List<String> tables = Arrays.asList(tableInput.split(","));

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/vueblog?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai", "root", "155346")
                // 全局配置
                .globalConfig(builder -> {
                    builder
                            .author("麋鹿")
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")
                            .commentDate("yyyy-MM-dd");
                })

                // 包名配置
                .packageConfig(builder ->
                        builder.parent("com.example")
                                .entity("entity")
                                .service("service")
                                .serviceImpl("service.impl")
                                .controller("controller")
                                .mapper("mapper")
                                .xml("mapper/xml")
                )

                // 策略配置
                .strategyConfig(builder -> {
                    builder
                            .addInclude(tables)
                            .entityBuilder()
                            .enableFileOverride()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .mapperBuilder()
                            .enableBaseResultMap()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .controllerBuilder()
                            .enableHyphenStyle()
                            .formatFileName("%sController")
                            .enableRestStyle();
                }).execute();
    }
}

