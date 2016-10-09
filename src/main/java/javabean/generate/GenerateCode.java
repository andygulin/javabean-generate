package javabean.generate;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;

import javabean.generate.Constants.Args;
import javabean.generate.bean.MySQLConnection;
import javabean.generate.bean.Table;
import javabean.generate.parse.GenerateJavaBean;
import javabean.generate.parse.Parse;

public class GenerateCode {
	public static void main(String[] args) {
		Options options = new Options();
		options.addOption(new Option(Args.HOST, true, StringUtils.EMPTY));
		options.addOption(new Option(Args.PORT, true, StringUtils.EMPTY));
		options.addOption(new Option(Args.DB, true, StringUtils.EMPTY));
		options.addOption(new Option(Args.USER, true, StringUtils.EMPTY));
		options.addOption(new Option(Args.PASSWD, true, StringUtils.EMPTY));
		options.addOption(new Option(Args.DIR, true, StringUtils.EMPTY));
		options.addOption(new Option(Args.PKG, true, StringUtils.EMPTY));

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		final String host = cmd.getOptionValue(Args.HOST, Args.DEFAULT_HOST);
		final String port = cmd.getOptionValue(Args.PORT, Args.DEFAULT_PORT);
		final String db = cmd.getOptionValue(Args.DB, Args.DEFAULT_DB);
		final String user = cmd.getOptionValue(Args.USER, Args.DEFAULT_USER);
		final String passwd = cmd.getOptionValue(Args.PASSWD, Args.DEFAULT_PASSWD);
		if (!cmd.hasOption(Args.DIR)) {
			System.out.printf("Args %s is Empty...", Args.DIR);
			System.exit(-1);
		}
		if (!cmd.hasOption(Args.PKG)) {
			System.out.printf("Args %s is Empty...", Args.PKG);
			System.exit(-1);
		}
		final File dir = new File(cmd.getOptionValue(Args.DIR));
		final String pkg = cmd.getOptionValue(Args.PKG);
		List<Table> tables = new Parse(new MySQLConnection(host, port, db, user, passwd)).getParseTables();
		try {
			List<File> outFiles = new GenerateJavaBean(tables).generate(dir, pkg);
			outFiles.forEach((file) -> System.out.println(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}