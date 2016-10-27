### 数据库表转JavaBean

### 打包
	mvn clean package
	
### 参数
	-host		数据库地址，默认localhost
	-port		数据库端口，默认3306
	-db			数据库名，默认test
	-user		数据库用户名，默认root
	-passwd		数据库密码，默认root
	-dir		JavaBean生成路径，默认当前目录
	-pkg		JavaBean包名，默认com.example
	
### 使用 & 完整参数
	java -jar target/javabean-generate-0.0.1-jar-with-dependencies.jar \
															-host localhost \
															-port 3306 \
															-db test \
															-user root \
															-passwd root \
															-dir D:/ \
															-pkg com.test