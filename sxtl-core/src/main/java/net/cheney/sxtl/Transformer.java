package net.cheney.sxtl;

import java.util.ArrayList;
import java.util.Arrays;

import net.cheney.snax.model.Document;
import net.cheney.snax.model.Element;
import net.cheney.snax.model.Namespace;
import net.cheney.snax.model.Node;

public class Transformer {

	public Document transform(Document document) {
		return new Document(transform(document.children()));
	}

	private Iterable<? extends Node> transform(Iterable<? extends Node> children) {
		ArrayList<Node> target = new ArrayList<Node>();
		for(Node child : children) {
			if(child.type().isElement()) {
				for(Node node : transform((Element) child)) {
					target.add(node);
				}
			} else {
				target.add(child);
			}
		}
		return target;
	}

	Iterable<? extends Node> transform(Element element) {
		Namespace namespace = element.namespace();
		NamespaceHandler handler = handlerForNamespace(namespace);
		return handler.process(element, new Context());
	}

	private NamespaceHandler handlerForNamespace(Namespace namespace) {
		return new NullHandler();
	}
	
	private static class NullHandler implements NamespaceHandler {

		public Iterable<? extends Node> process(Element element, Context ctx) {
			return Arrays.asList(element);
		}

		@Override
		public String toString() {
			return "NULL_HANDLER";
		}
		
	}
}
