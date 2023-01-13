package javabean.generate;

import com.beust.jcommander.JCommander;
import javabean.generate.bean.MySQLConnection;
import javabean.generate.bean.Table;
import javabean.generate.parse.GenerateJavaBean;
import javabean.generate.parse.Parse;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Log4j2
public class GenerateCode {
    public static void main(String[] args) {
        CommandOptions options = new CommandOptions();
        new JCommander(options).parse(args);

        List<Table> tables = new Parse(
                new MySQLConnection(
                        options.host,
                        options.port,
                        options.db,
                        options.user,
                        options.passwd
                )).getParseTables();
        log.info("Table Count : " + tables.size());
        try {
            new GenerateJavaBean(tables).generate(new File(options.output), options.pkg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}