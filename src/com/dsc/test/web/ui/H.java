/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.Label;

/**
 * @Author alex
 * @CreateTime Dec 27, 2016 4:35:08 PM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class H extends Label
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	public H(Context<? ,?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	public abstract int level();
}
