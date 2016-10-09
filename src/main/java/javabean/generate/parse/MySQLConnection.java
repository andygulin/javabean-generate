package javabean.generate.parse;

public class MySQLConnection {
	private String host;
	private String port;
	private String db;
	private String user;
	private String passwd;

	public MySQLConnection() {
		super();
	}

	public MySQLConnection(String host, String port, String db, String user, String passwd) {
		super();
		this.host = host;
		this.port = port;
		this.db = db;
		this.user = user;
		this.passwd = passwd;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}