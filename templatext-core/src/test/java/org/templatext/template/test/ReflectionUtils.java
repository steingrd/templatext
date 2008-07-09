package org.templatext.template.test;

import java.lang.reflect.Field;

public class ReflectionUtils {

	@SuppressWarnings("unchecked")
	public static <T> T getPrivateMember(Object obj, String memberName) throws IllegalArgumentException, IllegalAccessException {
		Class klass = obj.getClass();
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(memberName)) {
				field.setAccessible(true);
				return (T) field.get(obj);
			}
		}
		return null;
	}
	
}
