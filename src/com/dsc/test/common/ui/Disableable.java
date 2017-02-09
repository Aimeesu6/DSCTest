/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * VSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.TesteeHost;

/**
 * @Description
 * @Author alex
 * @CreateTime 06.12.2016 16:33:04
 * @Version 2.5
 * @Since 1.0
 */
public class Disableable extends UIObject
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	public Disableable(TesteeHost browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	public void disable(){
		setAttribute("disabled", "");
	}

	public void enable(){
		removeAttribute("disabled");
	}

	public boolean isDisabled(){
		return !isEnabled();
	}

	/**
	 * @return
	 */
	private boolean isEnabled()
	{
		return wrapee.isEnabled();
	}

}
