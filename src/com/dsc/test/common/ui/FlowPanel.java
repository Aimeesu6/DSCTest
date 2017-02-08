/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.ui.widget.Widget;
import com.dsc.test.web.Browser;

/**
 * @Author alex
 * @CreateTime Dec 30, 2014 7:52:25 PM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class FlowPanel extends Widget
{
	/**
	 * @param browser
	 * @param containerId
	 */
	public FlowPanel(Browser browser, String containerId)
	{
		super(browser, containerId);
	}

	public FlowPanel(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

}