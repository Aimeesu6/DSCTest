/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.ext;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

/**
 * The Class SuggestionBox.
 *
 * @Author alex
 * @CreateTime Aug 26, 2014 7:56:55 PM
 * @Version 1.0
 * @Since v1.0
 */
public class SuggestionBox extends RETextBox
{
	public SuggestionBox(Context<? ,?> context,WebElement wrapee)
	{
		super(context,wrapee);
	}
}