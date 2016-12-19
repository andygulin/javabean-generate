package javabean.generate;

import com.beust.jcommander.JCommander;
import javabean.generate.bean.MySQLConnection;
import javabean.generate.bean.Table;
import javabean.generate.parse.GenerateJavaBean;
import javabean.generate.parse.Parse;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GenerateCode {
    public static void main(String[] args) {
        CommandOptions options = new CommandOptions();
        new JCommander(options, args);

        List<Table> tables = new Parse(
                new MySQLConnection(options.host, options.port, options.db, options.user, options.passwd))
                .getParseTables();
        try {
            List<File> outFiles = new GenerateJavaBean(tables).generate(new File(options.dir), options.pkg);
            outFiles.forEach((file) -> System.out.println(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}