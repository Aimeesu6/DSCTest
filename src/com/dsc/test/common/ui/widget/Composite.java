/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.widget;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.TextBox;
import com.dsc.test.common.ui.UIObject;
import com.dsc.util.Util;

public abstract class Composite<T extends Context> extends Widget<T>
{

	public Composite(T context, String id)
	{
		super(context, id);
	}

	public Composite(T context, UIObject object){
		super(context,object);
	}

	public Composite(T context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	@Override
	public void ensureAvailable(){
		super.ensureAvailable();
		ensureChildrenAvailable();
	}

	protected void doEnsureAvailable(UIObject... objs){
		Util.mustNotNull(objs, "objs");

		for (UIObject obj : objs) {
			obj.ensureAvailable();
		}
	}

	protected WebElement elem(String id)
	{
		return context().findElemById(id);
	}

	protected abstract void ensureChildrenAvailable();

	protected void input(TextBox elem, String text)
	{
		elem.clear();
		elem.getWrapped().sendKeys(text);
	}
}