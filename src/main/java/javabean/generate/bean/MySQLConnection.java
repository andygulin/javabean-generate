package javabean.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MySQLConnection implements Serializable {

    private static final long serialVersionUID = 707757681293611120L;

    private String host;
    private String port;
    private String db;
    private String user;
    private String passwd;
}