/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.ext;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.TextBox;
import com.dsc.util.Log;

/**
 * @Author alex
 * @CreateTime Jan 17, 2015 10:33:51 PM
 * @Version 1.0
 * @Since 1.0
 */
public class RETextBox extends TextBox
{
	private static final String	DESCRIPTION_CSS	= "description";
	private static final String	REQUIRED		= "required";

	/**
	 * @param el
	 */
	public RETextBox(Context<? ,?> context,WebElement wrapee)
	{
		super(context,wrapee);
	}

	public void ensureAvailable(String description)
	{
		super.ensureAvailable();
		ensureDescriptionIs(description);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.UIObject#ensureAvailable()
	 */
	public void ensureAvailableAndRequested()
	{
		ensureAvailableAndRequested(null);
	}

	public void ensureAvailableAndRequested(String description)
	{
		ensureAvailable(description);

		if (notHasCss(REQUIRED))
		{
			throw new IllegalStateException(String.format("%s hasn't 'required' indicating css:%s in its css set:", id(),
					REQUIRED, css()));
		}
	}

	public void ensureDescriptionIs(String text)
	{
		if (containsCss(DESCRIPTION_CSS))
		{
			Log.info(String.format("%s is in default status currently due to css:'%s' contains '%s'", id(), css(),
					DESCRIPTION_CSS));
			if (textIsNot(text))
			{
				throw new IllegalStateException(String.format("%s's text isn't expected:' %s',but:'%s'", id(), text, text()));
			}
		} else if (textIs(text))
		{
			throw new IllegalStateException(
					String.format("'%s' is showing description:'%s',but it's css:'%s' doesn't contain '%s'", id(), text, css(),
							DESCRIPTION_CSS));
		}
	}
}