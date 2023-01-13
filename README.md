### 数据库表转JavaBean

### 打包
```
mvn clean package
```
	
### 参数
```
-host/-h            数据库地址，默认localhost
-port/-p            数据库端口，默认3306
-database/-db       数据库名，默认test
-user/-u            数据库用户名，默认root
-passwd	/-pw        数据库密码，默认root
-output/-o          JavaBean生成路径，默认当前目录
-package/-pkg       JavaBean包名，默认com.example
```
	
### 使用 & 完整参数
```
java -jar target/javabean-generate-0.0.1-jar-with-dependencies.jar \
                                                    -host localhost \
                                                    -port 3306 \
                                                    -database test \
                                                    -user root \
                                                    -passwd root \
                                                    -output D:/ \
                                                    -package com.test
```
	