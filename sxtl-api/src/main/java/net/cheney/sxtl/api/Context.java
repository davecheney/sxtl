package net.cheney.sxtl.api;

import java.util.HashMap;
import java.util.Map;

import net.cheney.snax.model.QName;

public class Context {

	Map<Object, Object> values = new HashMap<Object, Object>();
	
	public static Context emptyContext() {
		return new Context();
	}

	@SuppressWarnings("unchecked")
	public <T> T get(QName qname) {
		return (T) values.get(qname);
	}

	public Context put(QName qname, Object o) {
		values.put(qname, o);
		return this;
	}

}
