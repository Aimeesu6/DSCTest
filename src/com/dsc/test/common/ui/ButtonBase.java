/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.ui.widget.Widget;
import com.dsc.test.common.TesteeHost;

/**
 * @Author alex
 * @CreateTime Jan 16, 2015 6:42:36 PM
 * @Version 1.0
 * @Since 1.0
 */
public class ButtonBase extends Widget
{
	public ButtonBase(TesteeHost browser, String containerId)
	{
		super(browser, containerId);
	}

	/**
	 * @param browser
	 * @param wrapee
	 */
	public ButtonBase(TesteeHost browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	@Override
	public void click()
	{
		wrapee.click();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.UIObject#ensureTextIs(int)
	 */
	@Override
	public boolean ensureTextIs(String text)
	{
		return super.ensureTextIs(text);
	}

	public boolean isDisabled()
	{
		return !isEnabled();
	}

	/**
	 * @return
	 */
	public boolean isEnabled()
	{
		return wrapee.isEnabled();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.UIObject#text()
	 */
	@Override
	protected String text()
	{
		return super.text();
	}
}
