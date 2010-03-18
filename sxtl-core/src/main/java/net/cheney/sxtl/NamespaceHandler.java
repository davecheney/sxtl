package net.cheney.sxtl;

import net.cheney.snax.model.Element;
import net.cheney.snax.model.Node;
import net.cheney.sxtl.api.Context;

public interface NamespaceHandler {

	Iterable<? extends Node> process(Element element, Context ctx);
}
