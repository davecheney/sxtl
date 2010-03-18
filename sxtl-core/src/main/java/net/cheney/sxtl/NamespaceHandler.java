package net.cheney.sxtl;

import net.cheney.snax.model.Element;
import net.cheney.snax.model.Node;

public interface NamespaceHandler {

	Iterable<? extends Node> process(Element element, Context ctx);
}
