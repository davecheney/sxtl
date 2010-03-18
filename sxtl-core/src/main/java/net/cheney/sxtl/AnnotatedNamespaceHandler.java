package net.cheney.sxtl;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.cheney.snax.model.Element;
import net.cheney.snax.model.Node;
import net.cheney.sxtl.api.Method;

public class AnnotatedNamespaceHandler implements NamespaceHandler {

	private final Map<String, java.lang.reflect.Method> methods = new HashMap<String, java.lang.reflect.Method>();
	private final Object klazz;
	
	public AnnotatedNamespaceHandler(Object klazz) {
		this.klazz = klazz;
		for(java.lang.reflect.Method method : klazz.getClass().getMethods()) {
			for(Annotation annotation : method.getDeclaredAnnotations()) {
				if(annotation.annotationType().equals(Method.class)) {
					Method methodAnnotation = (Method) annotation;
					methods.put(methodAnnotation.value(), method);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<? extends Node> process(Element element, Context ctx) {
		try {
			String name = element.localpart();
			java.lang.reflect.Method method = methods.get(name);
//			LOG.debug("Calling "+method+" at "+element+" with context "+ctx.toString());
			return (Iterable<? extends Node>) method.invoke(klazz, new Object[] {element, ctx});
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

}