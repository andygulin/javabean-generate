package javabean.generate.parse;

public class MySQLConnection {
	private String host;
	private int port;
	private String user;
	private String passwd;

	public MySQLConnection() {
		super();
	}

	public MySQLConnection(String host, int port, String user, String passwd) {
		super();
		this.host = host;
		this.port = port;
		this.user = user;
		this.passwd = passwd;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
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
