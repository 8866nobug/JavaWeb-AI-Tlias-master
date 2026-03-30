# 项目结构说明

## 项目概述
本项目为自学练手项目，包含JavaWeb入门实践项目及自定义SpringBoot Starter相关模块，旨在提供JavaWeb基础学习案例及 Starter 开发实践参考。


## 文件夹说明

### 1. tlias-web-management
- 类型：JavaWeb入门项目
- 描述：基于JavaWeb技术栈的入门级项目（Tlias），包含Servlet、SpringMVC、MyBatis等基础技术的实践应用，适合新手学习JavaWeb开发流程与核心知识点。
- 主要功能：通常包含员工管理、部门管理等基础CRUD功能，用于演示Web应用的开发全流程。


### 2. aliyun-oss-spring-boot-autoconfigure
- 类型：自定义Starter核心配置模块
- 描述：阿里云OSS（对象存储服务）的SpringBoot自动配置模块，包含自动配置类、条件注解等核心逻辑，用于实现OSS功能的自动装配。
- 作用：根据项目依赖和配置文件，自动初始化OSS客户端等核心组件，简化OSS集成流程。


### 3. aliyun-oss-spring-boot-starter
- 类型：自定义Starter依赖管理模块
- 描述：阿里云OSS的SpringBoot Starter入口模块，主要用于管理`autoconfigure`模块及OSS官方SDK的依赖，提供"一键引入"能力。
- 作用：开发者只需在项目中引入该Starter依赖，即可快速集成阿里云OSS功能，无需手动维护复杂依赖。


### 4. springboot-autoconfiguration-test
- 类型：自定义Starter测试模块
- 描述：用于测试上述`aliyun-oss-spring-boot-starter`的验证项目，包含测试用例和示例代码。
- 作用：验证Starter的自动配置逻辑是否生效，确保OSS功能可正常使用，同时提供Starter的使用示例。


### 5. appendix
- 类型：附件文件夹
- 描述：包含项目相关的辅助材料，如：
    - 数据库脚本（SQL文件）
    - 项目运行截图
    - 配置示例（如application.properties模板）
- 百度云[下载连接](https://pan.baidu.com/s/1YppYAJbfbI9dfjSqQmIp4A?pwd=1234#list/path=%2F&parentPath=%2Fsharelink3232509500-61245778219326)

## 快速开始

**运行tlias-web-management**：
- 环境要求：JDK 8+、Maven 3.6+、MySQL 5.7+
- 步骤：
    1. 执行`appendix`中的数据库脚本初始化表结构
    2. 修改`application.properties`中的数据库配置
    3. 启动SpringBoot主类即可



## 相关资源
- B站教学视频：[Tlias教学管理系统项目](https://www.bilibili.com/video/BV1yGydYEE3H/?spm_id_from=333.788.video.desc.click&vd_source=2e2023c5a67cbf7a970987020070a609)
- JavaWeb学习文档：[SpringMVC官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- SpringBoot Starter开发指南：[Spring官方文档](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.developing-auto-configuration)
- 阿里云OSS SDK：[官方文档](https://help.aliyun.com/document_detail/32008.html)


## 注意事项
- 运行项目前请确保环境依赖（如JDK、数据库）已正确配置
- 自定义Starter开发需注意：
    - 版本兼容：确保Starter依赖的SpringBoot版本与项目一致
    - 依赖冲突：若出现依赖冲突，可通过`mvn dependency:tree`排查
