package javabean.generate;

import static javabean.generate.Constants.Args.DB;
import static javabean.generate.Constants.Args.DEFAULT_DB;
import static javabean.generate.Constants.Args.DEFAULT_DIR;
import static javabean.generate.Constants.Args.DEFAULT_HOST;
import static javabean.generate.Constants.Args.DEFAULT_PASSWD;
import static javabean.generate.Constants.Args.DEFAULT_PKG;
import static javabean.generate.Constants.Args.DEFAULT_PORT;
import static javabean.generate.Constants.Args.DEFAULT_USER;
import static javabean.generate.Constants.Args.DIR;
import static javabean.generate.Constants.Args.HOST;
import static javabean.generate.Constants.Args.PASSWD;
import static javabean.generate.Constants.Args.PKG;
import static javabean.generate.Constants.Args.PORT;
import static javabean.generate.Constants.Args.USER;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import javabean.generate.bean.MySQLConnection;
import javabean.generate.bean.Table;
import javabean.generate.parse.GenerateJavaBean;
import javabean.generate.parse.Parse;

public class GenerateCode {
	public static void main(String[] args) {
		Options options = new Options();
		final boolean hasArg = true;
		options.addOption(new Option(HOST, hasArg, EMPTY));
		options.addOption(new Option(PORT, hasArg, EMPTY));
		options.addOption(new Option(DB, hasArg, EMPTY));
		options.addOption(new Option(USER, hasArg, EMPTY));
		options.addOption(new Option(PASSWD, hasArg, EMPTY));
		options.addOption(new Option(DIR, hasArg, EMPTY));
		options.addOption(new Option(PKG, hasArg, EMPTY));

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		final String host = cmd.getOptionValue(HOST, DEFAULT_HOST);
		final String port = cmd.getOptionValue(PORT, DEFAULT_PORT);
		final String db = cmd.getOptionValue(DB, DEFAULT_DB);
		final String user = cmd.getOptionValue(USER, DEFAULT_USER);
		final String passwd = cmd.getOptionValue(PASSWD, DEFAULT_PASSWD);
		final File dir = new File(cmd.getOptionValue(DIR, DEFAULT_DIR));
		final String pkg = cmd.getOptionValue(PKG, DEFAULT_PKG);
		List<Table> tables = new Parse(new MySQLConnection(host, port, db, user, passwd)).getParseTables();
		try {
			List<File> outFiles = new GenerateJavaBean(tables).generate(dir, pkg);
			outFiles.forEach((file) -> System.out.println(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}