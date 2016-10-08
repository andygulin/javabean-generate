package ${package}

public class ${class} implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	<#list columns as item>
	private ${item.javaType} ${item.name};
	</#list>
	
	<#list columns as item>
	public void set${item.mName}(${item.javaType} ${item.name}) {
		this.${item.name} = ${item.name};
	}
	
	public ${item.javaType} get${item.mName}() {
		return ${item.name};
	}
	</#list>
}