/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui.gwt;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;
import com.dsc.selenium.ui.TextBox;
import com.dsc.selenium.ui.UIObject;
import com.dsc.selenium.util.Util;

public abstract class Composite extends Widget
{

	public Composite(Browser browser, String id)
	{
		super(browser, id);
	}

	public Composite(Browser browser, UIObject object){
		super(browser,object);
	}

	public Composite(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	@Override
	public void ensureAvailable(){
		super.ensureAvailable();
		ensureChildrenAvailable();
	}

	WebElement elem(String id)
	{
		return browser.findElemById(id);
	}

	protected void doEnsureAvailable(UIObject... objs){
		Util.mustNotNull(objs, "objs");

		for (UIObject obj : objs) {
			obj.ensureAvailable();
		}
	}

	protected abstract void ensureChildrenAvailable();

	protected void input(TextBox elem, String text)
	{
		elem.clear();
		elem.getWrapped().sendKeys(text);
	}
}