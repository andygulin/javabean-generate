package javabean.generate.template;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import javabean.generate.Constants;
import javabean.generate.bean.Column;
import javabean.generate.bean.Table;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.Introspector;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.CaseFormat.*;

@Log4j2
public class MakeTemplate {

    private Template template;

    public MakeTemplate() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        try {
            cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(), StringUtils.EMPTY));
            cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            template = cfg.getTemplate(Constants.TEMPLATE_FILE_NAME);
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
                column.setMName(LOWER_UNDERSCORE.to(UPPER_CAMEL, columnName));
            } else {
                column.setName(Introspector.decapitalize(columnName));
                column.setMName(columnName);
            }
            column.setMName(UPPER_UNDERSCORE.to(UPPER_CAMEL, column.getMName()));
        }
        root.put("columns", table.getColumns());
        Path parent = Paths.get(dir.getPath(), pkg.replace(Constants.PACKAGE_SEPARATOR, String.valueOf(IOUtils.DIR_SEPARATOR)));
        Files.createDirectories(parent);
        Path outFile = Paths.get(parent.toString(), className + Constants.JAVA_FILE_SUFFIX);
        BufferedWriter out = Files.newBufferedWriter(outFile);
        try {
            template.process(root, out);
        } catch (TemplateException e) {
            log.error(e.getMessage());
            log.error(className);
            log.error(table.getColumns());
        }
        out.flush();
        out.close();
        return outFile.toFile();
    }
}