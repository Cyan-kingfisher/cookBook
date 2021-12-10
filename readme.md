# 描述
front-end and back-end separation of the back-end project of the development project <br>
菜谱应用的后端，无页面。
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
![](https://img.shields.io/badge/technology-springboot-green)
![](https://img.shields.io/badge/technology-JPA-orange)
![](https://img.shields.io/badge/technology-lombok-red)
![](https://img.shields.io/badge/technology-mysql-9cf)
![](https://img.shields.io/badge/technology-redis-blueviolet)


## 运行环境及需要

1. MySql8
2. Maven
3. jdk11
4. Redis
5. 阿里云短信服务

## 功能

1. 登录（只能使用手机号验证码登录）
2. 搜索
3. 查看所有菜
4. 查看或者修改当前用户信息（包括头像，昵称等）
5. 查看当前用户上传的菜
6. 上传一道菜（需要有次序的使用两个接口）
7. 修改一道菜
8. 收藏一道菜
9. 对一道菜进行评论
10. 对菜，评论，收藏等删除
11， 查看某类别下所有菜

## 注意

文件夹prepare下有数据库表的结构和postman接口使用导出文件 <br>
src/../common下的Content.java可以修改图片存储位置<br>
resources/application-dev.properties修改阿里云短信服务信息<br>

