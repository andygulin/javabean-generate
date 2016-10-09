package javabean.generate;

public class Constants {

	public static final String DEFAULT_PACKAGE = "java.lang";
	public static final String PACKAGE_SEPARATOR = ".";
	public static final String JAVA_FILE_SUFFIX = "java";
	public static final String CAMELCASE_SYMBOL = "_";
	public static final String TEMPLATE_FILE_NAME = "JavaBean.ftlh";

	public static final class Args {
		public static final String HOST = "host";
		public static final String PORT = "port";
		public static final String DB = "db";
		public static final String USER = "user";
		public static final String PASSWD = "passwd";
		public static final String DIR = "dir";
		public static final String PKG = "pkg";

		public static final String DEFAULT_HOST = "localhost";
		public static final String DEFAULT_PORT = "3306";
		public static final String DEFAULT_DB = "test";
		public static final String DEFAULT_USER = "root";
		public static final String DEFAULT_PASSWD = "root";
	}
}
