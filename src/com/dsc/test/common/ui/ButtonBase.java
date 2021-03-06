/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.GeneralUIField;

/**
 * @Author alex
 * @CreateTime Jan 16, 2015 6:42:36 PM
 * @Version 1.0
 * @Since 1.0
 */
public class ButtonBase extends GeneralUIField
{
	public ButtonBase(Context<? ,?> context, String id)
	{
		this(context, context.findElemById(id));
	}

	/**
	 * @param browser
	 * @param wrapee
	 */
	public ButtonBase(Context<? ,?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	@Override
	public void click()
	{
		super.click();
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
}