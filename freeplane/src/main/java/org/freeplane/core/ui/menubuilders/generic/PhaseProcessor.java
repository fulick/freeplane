package org.freeplane.core.ui.menubuilders.generic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;

public class PhaseProcessor implements Processor{
	public static enum Phase {
		ACTIONS, ACCELERATORS, UI
	}

	final private LinkedHashMap<Phase, RecursiveMenuStructureProcessor> processors;

	public PhaseProcessor() {
		this.processors = new LinkedHashMap<Phase, RecursiveMenuStructureProcessor>();
	}

	@Override
	public void build(Entry entry) {
		for (RecursiveMenuStructureProcessor processor : processors.values())
			processor.build(entry);
	}

	@Override
	public Processor forChildren(Entry root, Entry entry) {
		final PhaseProcessor phaseProcessor = new PhaseProcessor();
		for (java.util.Map.Entry<Phase, RecursiveMenuStructureProcessor> processor : processors.entrySet())
			phaseProcessor.withPhase(processor.getKey(), processor.getValue().forChildren(root, entry));
		return phaseProcessor;
	}

	public PhaseProcessor withPhase(Phase phaseName, RecursiveMenuStructureProcessor processor) {
		processors.put(phaseName, processor);
		return this;
	}

	public RecursiveMenuStructureProcessor phase(Phase name) {
		return processors.get(name);
	}

	@Override
	public void destroy(Entry entry) {
		final ListIterator<RecursiveMenuStructureProcessor> processorIterator = new ArrayList<>(processors.values())
		    .listIterator(processors.size());
		while (processorIterator.hasPrevious())
			processorIterator.previous().destroy(entry);
	}
}
