package javabean.generate.parse;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TypeMapper {

	private static final Map<Integer, Class<?>> TYPE_MAPPERS = new HashMap<>();

	static {
		TYPE_MAPPERS.put(Types.VARCHAR, String.class);
		TYPE_MAPPERS.put(Types.CHAR, String.class);
		TYPE_MAPPERS.put(Types.BLOB, byte[].class);
		TYPE_MAPPERS.put(Types.INTEGER, Integer.class);
		TYPE_MAPPERS.put(Types.TINYINT, Integer.class);
		TYPE_MAPPERS.put(Types.SMALLINT, Integer.class);
		TYPE_MAPPERS.put(Types.BIT, Boolean.class);
		TYPE_MAPPERS.put(Types.BIGINT, BigInteger.class);
		TYPE_MAPPERS.put(Types.FLOAT, Float.class);
		TYPE_MAPPERS.put(Types.DOUBLE, Double.class);
		TYPE_MAPPERS.put(Types.DECIMAL, BigDecimal.class);
		TYPE_MAPPERS.put(Types.DATE, Date.class);
		TYPE_MAPPERS.put(Types.TIME, Date.class);
		TYPE_MAPPERS.put(Types.TIMESTAMP, Date.class);
	}

	public static Class<?> getMapperClass(int sqlType) {
		Class<?> clazz = null;
		for (Entry<Integer, Class<?>> entry : TYPE_MAPPERS.entrySet()) {
			if (entry.getKey().intValue() == sqlType) {
				clazz = entry.getValue();
				break;
			}
		}
		return clazz;
	}
}
