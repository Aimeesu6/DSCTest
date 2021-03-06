/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

/**
 * @Author alex
 * @CreateTime Dec 30, 2014 8:43:06 PM
 * @Version 1.0
 * @Since 1.0
 */
public class CheckBox extends ButtonBase
{

	/**
	 * @param wrapee
	 */
	public CheckBox(Context<? ,?> context,WebElement wrapee)
	{
		super(context,wrapee);
	}

	public boolean isSelected(){
		return wrapee.isSelected();
	}

	/**
	 * @param auto
	 */
	public void setValue(boolean checked)
	{
		if(wrapee.isSelected()!=checked)
		{
			wrapee.click();
		}
	}
}