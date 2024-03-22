package javabean.generate.parse;

import javabean.generate.Constants;
import javabean.generate.bean.Column;
import javabean.generate.bean.MySQLConnection;
import javabean.generate.bean.Table;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javabean.generate.Constants.*;

@Log4j2
public class Parse {

    private final MySQLConnection mySQLConnection;

    public Parse(MySQLConnection mySQLConnection) {
        this.mySQLConnection = mySQLConnection;
    }

    public List<Table> getParseTables() {
        List<Table> tables = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            rs = metaData.getTables(conn.getCatalog(), null, null, new String[]{"TABLE"});
            ResultSet colRs;
            List<Column> columns;
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                colRs = metaData.getColumns(null, "%", tableName, "%");
                columns = new ArrayList<>();
                while (colRs.next()) {
                    String colName = colRs.getString("COLUMN_NAME");
                    int sqlType = colRs.getInt("DATA_TYPE");
                    String javaType = TypeMapper.getMapperClass(sqlType).getName();
                    String pkg = substringBeforeLast(javaType);
                    if (pkg.equals(Constants.DEFAULT_PACKAGE)) {
                        javaType = substringAfterLast(javaType);
                    }
                    columns.add(new Column(colName, sqlType, javaType));
                }
                tables.add(new Table(tableName, columns));
                close(colRs);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        close(rs);
        close(conn);
        return tables;
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.err);
        }
        Connection conn = null;
        String url = "jdbc:mysql://%s:%s/%s?characterEncoding=utf-8&useSSL=false";
        url = String.format(url, mySQLConnection.getHost(), mySQLConnection.getPort(), mySQLConnection.getDb());
        try {
            conn = DriverManager.getConnection(url, mySQLConnection.getUser(), mySQLConnection.getPasswd());
            log.info("Connection DB Success...");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return conn;
    }

    private void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private String substringBeforeLast(final String str) {
        if (isEmpty(str) || isEmpty(PACKAGE_SEPARATOR)) {
            return str;
        }
        final int pos = str.lastIndexOf(PACKAGE_SEPARATOR);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos);
    }

    private String substringAfterLast(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        if (isEmpty(PACKAGE_SEPARATOR)) {
            return EMPTY_STRING;
        }
        final int pos = str.lastIndexOf(PACKAGE_SEPARATOR);
        if (pos == INDEX_NOT_FOUND || pos == str.length() - PACKAGE_SEPARATOR.length()) {
            return EMPTY_STRING;
        }
        return str.substring(pos + PACKAGE_SEPARATOR.length());
    }

    private boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}