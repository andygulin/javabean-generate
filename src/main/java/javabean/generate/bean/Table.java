package javabean.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Table implements Serializable {

    private static final long serialVersionUID = -8336625814475155226L;

    private String name;
    private List<Column> columns;
}