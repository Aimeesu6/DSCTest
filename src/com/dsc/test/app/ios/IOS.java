/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ios;

import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;
import static io.appium.java_client.remote.IOSMobileCapabilityType.BUNDLE_ID;
import static io.appium.java_client.remote.MobileBrowserType.SAFARI;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.UDID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import com.dsc.test.app.App;

import io.appium.java_client.ios.IOSDriver;

/**
 * @Author alex
 * @CreateTime Feb 11, 2017 11:34:16 PM
 * @Version 1.0
 * @Since 1.0
 */
public class IOS extends App<IOS, IOSDriver<RemoteWebElement>>
{
	public IOS()
	{
		super(DesiredCapabilities.iphone());
		platform(IOS);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#activity(java.lang.String)
	 */
	@Override
	public IOS activity(String activity)
	{
		// android only,not applied for IOS
		return this;
	}

	@Override
	public String browserName()
	{
		return SAFARI;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.dsc.test.common.Context#decorator(org.openqa.selenium.SearchContext)
	 */
	@Override
	public FieldDecorator decorator(SearchContext searchCxt)
	{
		return new IOSFieldDecorator(this, searchCxt);
	}

	// usually recommended for versions>10.X.
	public IOS forNewVersion()
	{
		setCapability(AUTOMATION_NAME, IOS_XCUI_TEST);
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#isLocked()
	 */
	@Override
	public boolean isLocked()
	{
		throw new RuntimeException("'isLocked()' is android only,not applied for IOS");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#lockDevice(int)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void lockDevice(int seconds)
	{
		driver.lockDevice(seconds);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#shake()
	 */
	@Override
	public void shake()
	{
		driver.shake();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#UDID(java.lang.String)
	 */
	@Override
	public IOS UDID(String udid)
	{
		setCapability(UDID, udid);

		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#unlockDevice()
	 */
	@Override
	public void unlockDevice()
	{
		// android only,not applied for IOS
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#createDriver(java.lang.String)
	 */
	@Override
	protected IOSDriver<RemoteWebElement> createDriver(String remoteAddress) throws MalformedURLException
	{
		return new IOSDriver<>(new URL(remoteAddress), cap);
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.app.App#pageId()
	 */
	@Override
	protected String pageId()
	{
		// android only,not applied for IOS
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#pkgOrBundleId()
	 */
	@Override
	protected String pkgOrBundle()
	{
		return BUNDLE_ID;
	}
}