/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.widget;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.dsc.test.common.pagefactory.WebElementDecorator;
import com.dsc.test.common.ui.UIObject;
import com.dsc.test.common.TesteeHost;

/**
 * @Author alex
 * @CreateTime Jan 5, 2015 11:15:04 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Widget extends UIObject
{
	public Widget(TesteeHost browser, String containerId)
	{
		this(browser, browser.findElemById(containerId));
	}

	public Widget(TesteeHost browser, UIObject wrapee){
		this(browser,wrapee.element());
	}

	public Widget(TesteeHost browser, WebElement wrapee)
	{
		super(browser, wrapee);
		// initialize all fields
		PageFactory.initElements(new WebElementDecorator(browser,this.element()), this);
	}
}
