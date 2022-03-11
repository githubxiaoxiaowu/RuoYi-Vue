package com.ruoyi.core.gen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-09 17:14
 **/
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }



    @Data
    public static class Config{
        private String project="ruoyi-armarket-core";
        private String packageName="com.ruoyi.code";
        private String jdbcUrl="jdbc:mysql://192.168.16.160:3306/ry-vue?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        private String dbUserName="wuzhendong";
        private String dbPassword="wuzhendong";
        private String driverName="com.mysql.cj.jdbc.Driver";

        private String superEntityClass="com.ruoyi.code.domain.BasePlusEntity";

        private String[] superEntityColumns=new String[] { "id", "create_time","optimistic","update_time","user_id" };

    }


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        Config config=new Config();
        String project = config.getProject();
        String packageName = config.getPackageName();
        String projectPath = System.getProperty("user.dir") + "/" + project+"/gen";
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath );
        gc.setAuthor("zhendong.wu");
        gc.setIdType(IdType.NONE);//主键自增
        gc.setOpen(false);
        gc.setSwagger2(false); // 实体属性 Swagger2 注解
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);
        // gc.setFileOverride(Boolean.valueOf(scanner("是否需要覆盖文件:")));

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // url: jdbc:mysql://dev-aden-mysql.dev-aden.svc.cluster.local:3306/aden_cust_core?characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai

        dsc.setUrl(config.getJdbcUrl());
        // dsc.setSchemaName("public");
        dsc.setDriverName(config.getDriverName());
        dsc.setUsername(config.getDbUserName());
        dsc.setPassword(config.getDbPassword());
        dsc.setTypeConvert(new NewMysqlTypeConvert());
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        //  pc.setModuleName(scanner("模块名"));
        pc.setEntity("domain");
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/mapper/" // + pc.getModuleName() + "/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        // condition_entrust_order,condition_order_log,condition_order
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(config.getSuperEntityClass());
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段 - basic 生成注释
        strategy.setSuperEntityColumns(config.getSuperEntityColumns()); //写的是数据库字段名
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);

//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
// basic_four_elements_auth
        mpg.execute();
    }

    public static class NewMysqlTypeConvert extends MySqlTypeConvert {

        @Override
        public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {

            return Optional.ofNullable( findBy(globalConfig,fieldType)).orElseGet(()->super.processTypeConvert(globalConfig,fieldType));
        }
        private IColumnType findBy(GlobalConfig globalConfig, String fieldType){
            String t = fieldType.toLowerCase();
            if (t.contains("date") || t.contains("time") || t.contains("year")) {
                switch (globalConfig.getDateType()) {
                    case ONLY_DATE:
                        return DbColumnType.DATE;
                    case SQL_PACK:
                        switch (t) {
                            case "date":
                                return DbColumnType.DATE;
                            case "time":
                                return DbColumnType.DATE;
                            case "year":
                                return DbColumnType.DATE;
                            default:
                                return DbColumnType.DATE;
                        }
                    case TIME_PACK:
                        switch (t) {
                            case "date":
                                return DbColumnType.DATE;
                            case "time":
                                return DbColumnType.DATE;
                            case "year":
                                return DbColumnType.YEAR;
                            default:
                                return DbColumnType.DATE;
                        }
                }
            }
            return null;
        }
    }

}
