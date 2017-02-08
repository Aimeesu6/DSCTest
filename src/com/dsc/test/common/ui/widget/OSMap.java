/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.widget;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.dsc.test.common.ui.Button;
import com.dsc.test.common.ui.Canvas;
import com.dsc.test.web.Browser;
import com.dsc.util.Util;

/**
 * @Author alex
 * @CreateTime Jun 3, 2016 11:45:35 AM
 * @Version 1.0
 * @Since 1.0
 */
public class OSMap extends Composite
{
	//NP alex   add  "protected",delete "legend"
	@FindBy(tagName = "canvas")
	protected Canvas	canvas;
	@FindBy(className = "ol-zoom-in")
	protected Button	zoomIn;
	@FindBy(className = "ol-zoom-out")
	protected Button	zoomOut;

	/**
	 * @param browser
	 * @param wrapee
	 */
	public OSMap(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	public void dragAndDropMap(int xOffset, int yOffset){
		canvas.dragAndDrop(xOffset, yOffset);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.widget.gwt.Composite#ensureElementsAvailable()
	 */
	@Override
	protected void ensureChildrenAvailable()
	{
		canvas.ensureAvailable();
		// attributions;
		zoomIn.ensureAvailable();
		zoomOut.ensureAvailable();
		//NP
		Util.sleep(3,"canvas content");
	}
}