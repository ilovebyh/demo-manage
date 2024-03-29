package com.byh.utils;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

public class CodeGenerator {

    //代码生成路径
    private static String mapperPath;
    private static String mapperXmlPath;
    private static String servicePath;
    private static String serviceImplPath;
    private static String controllerPath;

    static{
        //加载路径
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(CodeGenerator.class.getClassLoader().getResourceAsStream("generator.properties"),"utf-8"));
            mapperPath = properties.getProperty("gen.mapper.path");
            mapperXmlPath = properties.getProperty("gen.mapperXml.path");
            servicePath = properties.getProperty("gen.service.path");
            serviceImplPath = properties.getProperty("gen.serviceImpl.path");
            controllerPath = properties.getProperty("gen.controller.path");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //代码生成的先后顺序
    private static final String[] paths = {
        mapperPath,
        mapperXmlPath,
        servicePath,
        serviceImplPath,
        controllerPath
    };
    //模板先后顺序
    private static final String[] templates = {
        "Mapper.java.vm",
        "Mapper.xml.vm",
        "Service.java.vm",
        "ServiceImpl.java.vm",
        "Controller.java.vm"
    };

    //要生成的那些实体类的相关代码
    private static final String[] domains = {"Operationlog"};

    //是否覆盖
    private static final boolean FLAG = true;

    @Test
    public void test() throws Exception{

        //模板上下文
        VelocityContext context = new VelocityContext();
        //遍历所有的domain
        for (String domain : domains) {
            //拿到domain类名的大小写
            String DomainName = domain;
            String domainName = domain.substring(0,1).toLowerCase()+
                    domain.substring(1);
            //上下文中设置替换内容
            context.put("Domain",DomainName);
            context.put("domain",domainName);
            //遍历路径，拿到模板生成目标文件
            for (int i=0;i<paths.length;i++) {

                //初始化参数
                Properties properties=new Properties();
                //设置velocity资源加载方式为class
                properties.setProperty("resource.loader", "class");
                //设置velocity资源加载方式为file时的处理类
                properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
                //实例化一个VelocityEngine对象
                VelocityEngine velocityEngine=new VelocityEngine(properties);

                String templateName = templates[i];
                //生成代码
                //生成的文件名
                String fileName = templateName.substring(0, templateName.lastIndexOf(".vm"));
                String filePath = paths[i]+"\\"+fileName;
                filePath = filePath.replaceAll("domain", domainName).
                        replaceAll("Domain", DomainName);
                File file = new File(filePath);

                System.out.println(filePath);

                //判断是否覆盖存在的文件
                if(file.exists()&&!FLAG){
                    continue;
                }

                //获取父目录
                File parentFile = file.getParentFile();
                if(!parentFile.exists()){
                    parentFile.mkdirs();
                }
                Writer writer = new FileWriter(file);
                //拿到模板，设置编码
                velocityEngine.mergeTemplate("velocity/"+templateName,"utf-8",context,writer);
                writer.close();

            }

        }

    }

}
