# smart-admin

[![master Build Status](https://travis-ci.org/gaoxingyun/smart-admin.svg?branch=master)](https://travis-ci.org/gaoxingyun/smart-admin)
[![dev Build Status](https://travis-ci.org/gaoxingyun/smart-admin.svg?branch=dev)](https://travis-ci.org/gaoxingyun/smart-admin)

#### 目标

- 目标是做一个简单的管理系统，可以轻松的集成到spring-boot的项目中，可以通过后台来编辑页面，和直接执行原始sql语句。目标是做成一个类似[https://github.com/la-team/light-admin](https://github.com/la-team/light-admin)的项目。


#### 模块

- smart-admin-core 核心模块
- smart-admin-starter spring-boot自动配置模块
- smart-admin-boot spring-boot运行模块


#### 快速开始

###### gradle

- 添加自定义maven仓库
``` gradle
maven{ url 'https://raw.github.com/gaoxingyun/repo/mvn-repo'}
```
- 添加依赖
``` gradle
compile('top.ggstar:smart-admin-starter')
```
- 打开地址 
```
http://${IP}:${PORT}/smart-admin
```
