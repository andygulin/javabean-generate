### 数据库表转JavaBean

### 打包
	mvn clean package
	
### 参数
	-host		数据库地址，默认localhost
	-port		数据库端口，默认3306
	-db			数据库名，默认test
	-user		数据库用户名，默认root
	-passwd		数据库密码，默认root
	-dir		JavaBean生成路径，必须存在
	-pkg		JavaBean包名，必须存在
	
### 使用
	java -jar target/javabean-generate-0.0.1-jar-with-dependencies.jar -dir D:/ -pkg com.test