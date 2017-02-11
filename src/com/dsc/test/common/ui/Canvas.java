/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

public class Canvas extends UIObject
{

	public Canvas(Context context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	/*
	 * make protected method public
	 */
	@Override
	public void dragAndDrop(int xOffset, int yOffset)
	{
		super.dragAndDrop(xOffset, yOffset);
	}
}
