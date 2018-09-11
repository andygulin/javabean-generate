package javabean.generate.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Column implements Serializable {

    private static final long serialVersionUID = 578742059568123887L;

    private String name;
    private String mName;
    private int sqlType;
    private String javaType;

    public Column(String name, int sqlType, String javaType) {
        super();
        this.name = name;
        this.sqlType = sqlType;
        this.javaType = javaType;
    }
}