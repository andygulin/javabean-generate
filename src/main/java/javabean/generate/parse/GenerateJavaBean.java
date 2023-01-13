package javabean.generate.parse;

import javabean.generate.bean.Table;
import javabean.generate.template.MakeTemplate;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Log4j2
public class GenerateJavaBean {

    private final List<Table> tables;

    public GenerateJavaBean(List<Table> tables) {
        this.tables = tables;
    }

    public void generate(File dir, String pkg) throws IOException {
        MakeTemplate makeTemplate = new MakeTemplate();
        for (Table table : tables) {
            File file = makeTemplate.make(table, dir, pkg);
            log.info("Generate File : {}", file);
        }
    }
}