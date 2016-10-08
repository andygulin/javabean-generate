package javabean.generate.bean;

import java.util.List;

public class Table {

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
