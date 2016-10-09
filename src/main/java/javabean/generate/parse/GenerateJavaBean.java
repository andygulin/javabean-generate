package javabean.generate.parse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javabean.generate.bean.Table;
import javabean.generate.template.MakeTemplate;

public class GenerateJavaBean {

	private List<Table> tables;

	public GenerateJavaBean(List<Table> tables) {
		this.tables = tables;
	}

	public List<File> generate(File dir, String pkg) throws IOException {
		List<File> outFiles = new ArrayList<>();
		MakeTemplate makeTemplate = new MakeTemplate();
		for (Table table : tables) {
			outFiles.add(makeTemplate.make(table, dir, pkg));
		}
		return outFiles;
	}
}
