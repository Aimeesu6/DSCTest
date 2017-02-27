/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.ValidableBase;

import io.appium.java_client.MobileElement;

/**
 * The Class TextBox.
 */
public class TextBox extends ValidableBase
{

	/**
	 * Instantiates a new text box.
	 *
	 * @param browser
	 *            the browser
	 * @param wrapee
	 *            the wrapee
	 */
	public TextBox(Context<?, ?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	/**
	 * Clear.
	 *
	 * @return the text box
	 */
	public TextBox clear()
	{
		wrapee.clear();
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.UIObject#ensureTextIs(java.lang.String)
	 */
	@Override
	public boolean ensureTextIs(String text)
	{
		return super.ensureTextIs(text);
	}

	/**
	 * Input.
	 *
	 * @param text
	 *            the text
	 */
	@Override
	public void input(String text)
	{
		clear();

		//due to
		if (wrapee instanceof MobileElement)
		{
			((MobileElement) wrapee).setValue(text);
		} else
		{
			wrapee.sendKeys(text);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.UIObject#text()
	 */
	@Override
	public String text()
	{
		return super.text();
	}
}