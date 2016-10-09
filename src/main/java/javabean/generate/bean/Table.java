package javabean.generate.bean;

import java.io.Serializable;
import java.util.List;

public class Table implements Serializable {

	private static final long serialVersionUID = -8336625814475155226L;

	private String name;
	private List<Column> columns;

	public Table() {
		super();
	}

	public Table(String name, List<Column> columns) {
		super();
		this.name = name;
		this.columns = columns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

}
