package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;

public class PasswordBox extends TextBox
{
	public PasswordBox(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}
}