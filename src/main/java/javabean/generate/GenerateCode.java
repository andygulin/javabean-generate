package javabean.generate;

import java.io.File;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.math.NumberUtils;

import javabean.generate.bean.Table;
import javabean.generate.parse.GenerateJavaBean;
import javabean.generate.parse.MySQLConnection;
import javabean.generate.parse.Parse;

public class GenerateCode {
	public static void main(String[] args) throws ParseException {
		Options options = new Options();
		options.addOption(new Option("host", true, "数据库地址"));
		options.addOption(new Option("port", true, "数据库端口"));
		options.addOption(new Option("user", true, "数据库用户名"));
		options.addOption(new Option("passwd", true, "数据库密码"));
		options.addOption(new Option("dir", true, "生成路径"));
		options.addOption(new Option("pkg", true, "Java包名"));

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);
		final String host = cmd.getOptionValue("host", "localhost");
		final int port = NumberUtils.toInt(cmd.getOptionValue("port", "3306"));
		final String user = cmd.getOptionValue("user", "root");
		final String passwd = cmd.getOptionValue("passwd", "root");
		final File dir = new File(cmd.getOptionValue("dir"));
		final String pkg = cmd.getOptionValue("pkg");
		List<Table> tables = new Parse(new MySQLConnection(host, port, user, passwd)).getParseTables();
		new GenerateJavaBean(tables).generate(dir, pkg);
	}
}