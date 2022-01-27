package com.ipinyou.user.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FastAutoGeneratorTest {
    private static final DataSourceConfig datasource;

    private static final String MODULE_NAME = "";

    private static final String tablePrefix = "";

    private static final String author = "wTai";

    private static final String[] tables = {
            "user_base"
    };

    private static final boolean override = true;

    static {
        // 数据源
        datasource = new DataSourceConfig();
        datasource.setUrl("jdbc:mysql://121.37.226.39:3306/user?autoReconnect=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai");
        datasource.setSchemaName("");
        datasource.setDriverName("com.mysql.cj.jdbc.Driver");
        datasource.setUsername("root");
        datasource.setPassword("root");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 数据源配置
        autoGenerator.setDataSource(datasource);

        // 全局配置，注意路径添加了模块路径
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/user";
        final String servicePath = projectPath;
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor(author);
        globalConfig.setOpen(false);
        globalConfig.setBaseColumnList(true);
        globalConfig.setBaseResultMap(true);
        globalConfig.setFileOverride(override);
        globalConfig.setEntityName("%sEntity");
        globalConfig.setMapperName("%sDAO");
        autoGenerator.setGlobalConfig(globalConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("");
        packageConfig.setParent("com.ipinyou.user");
        packageConfig.setEntity(MODULE_NAME + "entity");
        packageConfig.setMapper(MODULE_NAME + "repository.mysql");
        packageConfig.setService(MODULE_NAME + "service");
        packageConfig.setServiceImpl(MODULE_NAME + "service.impl");
        autoGenerator.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = tableInfo.getEntityName();
                String mapperName = entityName.substring(0, entityName.length() - "Entity".length());
                // 自定义输入文件名称
                String mapperFile = servicePath + "/src/main/resources/mapper/";
                if (StringUtils.isNotBlank(packageConfig.getModuleName())) {
                    mapperFile += "/" + packageConfig.getModuleName();
                }
                mapperFile += "/" + mapperName + "Mapper" + StringPool.DOT_XML;
                return mapperFile;
            }
        });
        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);
        autoGenerator.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.ipinyou.user.comon.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setSuperEntityColumns("is_deleted", "create_time", "update_time", "create_by", "update_by", "version");
        strategy.setTablePrefix(tablePrefix);
        strategy.setInclude(tables);

        // version和delete已经定义在BaseEntity中了
//        strategy.setVersionFieldName("version");
//        strategy.setLogicDeleteFieldName("deleted");
        // 不需要生成controller层
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);

        autoGenerator.setStrategy(strategy);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        autoGenerator.execute();
    }
}
