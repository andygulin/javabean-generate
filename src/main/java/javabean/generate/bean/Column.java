package javabean.generate.bean;

import java.io.Serializable;

public class Column implements Serializable {

	private static final long serialVersionUID = 578742059568123887L;

	private String name;
	private String mName;
	private int sqlType;
	private String javaType;

	public Column() {
		super();
	}

	public Column(String name, int sqlType, String javaType) {
		super();
		this.name = name;
		this.sqlType = sqlType;
		this.javaType = javaType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getSqlType() {
		return sqlType;
	}

	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

}
