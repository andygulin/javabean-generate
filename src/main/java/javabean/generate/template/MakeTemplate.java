package javabean.generate.template;

import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

import java.beans.Introspector;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import javabean.generate.Constants;
import javabean.generate.bean.Column;
import javabean.generate.bean.Table;

public class MakeTemplate {

	private Template temp;

	public MakeTemplate() {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		Resource resource = new ClassPathResource(Constants.TEMPLATE_FILE_PATH);
		try {
			String filePath = resource.getFile().getPath();
			String fileDir = filePath.substring(0, filePath.lastIndexOf("\\"));
			cfg.setDirectoryForTemplateLoading(new File(fileDir));
			cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
			temp = cfg.getTemplate(resource.getFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File make(Table table, File dir, String pkg) throws IOException {
		Map<String, Object> root = new HashMap<>();
		root.put("package", pkg);
		String className = LOWER_UNDERSCORE.to(UPPER_CAMEL, table.getName());
		root.put("class", className);
		for (Column column : table.getColumns()) {
			String columnName = column.getName();
			if (StringUtils.indexOf(columnName, Constants.CAMELCASE_SYMBOL) != StringUtils.INDEX_NOT_FOUND) {
				column.setName(Introspector.decapitalize(LOWER_UNDERSCORE.to(UPPER_CAMEL, columnName)));
				column.setmName(LOWER_UNDERSCORE.to(UPPER_CAMEL, columnName));
			} else {
				column.setName(Introspector.decapitalize(columnName));
				column.setmName(columnName);
			}
		}
		root.put("columns", table.getColumns());
		File parent = new File(dir, pkg.replace(Constants.PACKAGE_SEPARATOR, String.valueOf(IOUtils.DIR_SEPARATOR)));
		FileUtils.forceMkdir(parent);
		File outFile = new File(parent, className + FilenameUtils.EXTENSION_SEPARATOR_STR + Constants.JAVA_FILE_SUFFIX);
		Writer out = new FileWriter(outFile);
		try {
			temp.process(root, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
		return outFile;
	}
}
