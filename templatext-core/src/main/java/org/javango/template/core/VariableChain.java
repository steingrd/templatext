package org.javango.template.core;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class VariableChain {

	private List<String> chain;

	public VariableChain(List<String> chain) {
		this.chain = chain;
	}

	public Object resolve(Object object) {
		for (String name : chain) {
			object = resolveInternal(object, name);
		}
		return object;
	}

	private Object resolveInternal(Object object, String name) {
		if (isIndex(object, name)) {
			return resolveIndex(object, name);
		}
		
		if (isMember(object, name)) {
			return resolveMember(object, name);
		}

		if (isProperty(object, name)) {
			return resolveProperty(object, name);
		}
		
		if (isMappedProperty(object, name)) {
			return resolveMappedProperty(object, name);
		}

		if (isMethod(object, name)) {
			return invokeMethod(object, name);
		}
		
		return "";
	}

	private boolean isIndex(Object object, String name) {
		try {
			Integer.parseInt(name);
		} catch (NumberFormatException e) {
			return false;
		}
		
		return object.getClass().isArray();
	}
	
	private Object resolveIndex(Object object, String name) {
		int index = Integer.parseInt(name);
		return Array.get(object, index);
	}

	private boolean isMember(Object object, String name) {
		// special case for arrays
		if (object.getClass().isArray() && name.equals("length")) {
			return true;
		}
		
		try {
			object.getClass().getField(name);
			return true;
		} catch (SecurityException e) {
			return false;
		} catch (NoSuchFieldException e) {
			return false;
		}
	}

	private Object resolveMember(Object object, String name) {
		// special case for arrays
		if (object.getClass().isArray() && name.equals("length")) {
			return Array.getLength(object);
		}
		
		try {
			Field field = object.getClass().getField(name);
			return field.get(object);
		} catch (SecurityException e) {
			return "";
		} catch (NoSuchFieldException e) {
			return "";
		} catch (IllegalArgumentException e) {
			return "";
		} catch (IllegalAccessException e) {
			return "";
		}
	}

	private boolean isProperty(Object object, String name) {
		return PropertyUtils.isReadable(object, name);
	}

	private Object resolveProperty(Object object, String name) {
		try {
			return PropertyUtils.getSimpleProperty(object, name);
		} catch (IllegalAccessException e) {
			return "";
		} catch (InvocationTargetException e) {
			return "";
		} catch (NoSuchMethodException e) {
			return "";
		}
	}

	private boolean isMappedProperty(Object object, String name) {
		return Map.class.isAssignableFrom(object.getClass());
	}

	private Object resolveMappedProperty(Object object, String name) {
		Map<?,?> map = (Map<?,?>)object;
		if (map.containsKey(name)) {
			return map.get(name);
		}
		
		return ""; // TODO find out if this is the correct behaviour
	}
	
	private boolean isMethod(Object object, String name) {
		try {
			object.getClass().getMethod(name);
			return true;
		} catch (SecurityException e) {
			return false;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

	private Object invokeMethod(Object object, String name) {
		try {
			return object.getClass().getMethod(name).invoke(object);
		} catch (IllegalArgumentException e) {
			return "";
		} catch (SecurityException e) {
			return "";
		} catch (IllegalAccessException e) {
			return "";
		} catch (InvocationTargetException e) {
			return "";
		} catch (NoSuchMethodException e) {
			return "";
		}
	}

}
