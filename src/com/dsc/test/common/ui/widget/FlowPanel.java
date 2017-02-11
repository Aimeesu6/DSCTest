/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.widget;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

/**
 * @Author alex
 * @CreateTime Dec 30, 2014 7:52:25 PM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class FlowPanel extends GeneralWidget
{
	/**
	 * @param context
	 * @param containerId
	 */
	public FlowPanel(Context context, String containerId)
	{
		super(context, containerId);
	}

	public FlowPanel(Context context, WebElement wrapee)
	{
		super(context, wrapee);
	}

}