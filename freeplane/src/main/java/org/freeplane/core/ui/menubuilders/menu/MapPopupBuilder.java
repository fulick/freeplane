package org.freeplane.core.ui.menubuilders.menu;

import org.freeplane.core.ui.IUserInputListenerFactory;
import org.freeplane.core.ui.menubuilders.generic.Entry;
import org.freeplane.core.ui.menubuilders.generic.EntryAccessor;
import org.freeplane.core.ui.menubuilders.generic.EntryVisitor;

public class MapPopupBuilder implements EntryVisitor {
	private final IUserInputListenerFactory userInputListenerFactory;

	public MapPopupBuilder(IUserInputListenerFactory userInputListenerFactory) {
		super();
		this.userInputListenerFactory = userInputListenerFactory;
	}

	@Override
	public void visit(Entry target) {
		new EntryAccessor().setComponent(target, userInputListenerFactory.getMapPopup());
	}

	@Override
	public boolean shouldSkipChildren(Entry entry) {
		return false;
	}

}
