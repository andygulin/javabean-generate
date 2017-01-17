package javabean.generate;

import com.beust.jcommander.Parameter;

public class CommandOptions {

    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "3306";
    private static final String DEFAULT_DB = "test";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWD = "root";
    private static final String DEFAULT_DIR = "";
    private static final String DEFAULT_PKG = "com.example";

    @Parameter(names = {"-host", "-h"})
    public String host = DEFAULT_HOST;

    @Parameter(names = {"-port", "-p"})
    public String port = DEFAULT_PORT;

    @Parameter(names = {"-database", "-db"})
    public String db = DEFAULT_DB;

    @Parameter(names = {"-user", "-u"})
    public String user = DEFAULT_USER;

    @Parameter(names = {"-passwd", "-pw"})
    public String passwd = DEFAULT_PASSWD;

    @Parameter(names = {"-dir", "-d"})
    public String dir = DEFAULT_DIR;

    @Parameter(names = {"-package", "-pkg"})
    public String pkg = DEFAULT_PKG;
}
