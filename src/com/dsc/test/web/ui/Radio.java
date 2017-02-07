/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;

/**
 * @Author alex
 * @CreateTime Dec 30, 2014 8:43:06 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Radio extends CheckBox
{

	/**
	 * @param wrapee
	 */
	public Radio(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}
}