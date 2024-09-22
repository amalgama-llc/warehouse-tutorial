package com.company.warehouse.testsapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestsGroupTreeElement extends TreeElement{
	
	private List<TreeElement> childElements = new ArrayList<>();
	
	public TestsGroupTreeElement(String name) {
		super(name);
	}
	
	public List<TreeElement> getChildElements() {
		return childElements;
	}

	@Override
	public RunResult getRunResult() {
		var runResults = childElements.stream().map(treeElement -> treeElement.getRunResult()).filter(runResult -> runResult != RunResult.NONE).collect(Collectors.toList());
		return 	runResults.isEmpty() ? RunResult.NONE
				: runResults.contains(RunResult.ERROR) ? RunResult.ERROR
				: runResults.contains(RunResult.FAILED) ? RunResult.FAILED
				: RunResult.PASSED;
	}
}

