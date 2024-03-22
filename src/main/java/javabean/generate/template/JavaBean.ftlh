package ${package};

public class ${class} {

<#list columns as item>
    private ${item.javaType} ${item.name};
</#list>

<#list columns as item>
    public void set${item.MName}(${item.javaType} ${item.name}) {
        this.${item.name} = ${item.name};
    }

    public ${item.javaType} get${item.MName}() {
        return ${item.name};
    }
</#list>