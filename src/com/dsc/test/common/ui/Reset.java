/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

public class Reset extends ButtonBase
{
	public Reset(Context<?, ?> context, String id)
	{
		this(context, context.findElemById(id));
	}

	/**
	 * @param browser
	 * @param wrapee
	 */
	public Reset(Context<?, ?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}
}