/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web

import static com.dsc.util.FileUtil.*
import static com.dsc.util.ImageUtil.*
import static com.dsc.util.Util.*
import static java.lang.String.*

import java.awt.image.BufferedImage
import java.text.SimpleDateFormat

import javax.imageio.ImageIO

import org.apache.commons.io.FileUtils

import com.dsc.test.TestStub
import com.dsc.util.ImageUtil
import com.dsc.util.Log

import spock.lang.Shared

/**
 * The Class WebTestStub.
 *
 * @Author alex
 * @CreateTime Aug 26, 2014 7:59:11 PM
 * @Version 1.0
 * @Since v1.0
 */
class WebTestStub  extends TestStub {
	// NP get from configuration file
	private static final String	SCREENSHOT_DIR				= "screenshots/"
	private static final String	STANDARD_IMAGES_DIR				= "standard_images/"

	//	@Shared Browser browser= Browser.firfox()
	@Shared Browser browser= Browser.chrome()
	@Shared boolean takeScreenshot=false

	def tryToCreateScreenshotDir() {

		File dir = new File(screenshotsDir())

		if (dir.exists()) {
			Log.info(String.format("screenshot directory %s existed already,nothing will be done", screenshotsDir()))
		} else {
			if (dir.mkdirs()) {
				Log.info(String.format("screenshot directory %s created successfully!!!!!!!!!!!!!", screenshotsDir()))
			} else {
				Log.info(String.format("screenshot directory %s creatiing failed!!!!!!!!!!!!!!!!!!", screenshotsDir()))
			}
		}
	}

	/**
	 * @return
	 */
	String screenshotsDir() {
		return SCREENSHOT_DIR + formatTime("yyyy/MM/dd/")
	}

	boolean takeScreenshot(){
		if(takeScreenshot){

			tryToCreateScreenshotDir()

			if (standardImage().exists()) {//standard image exists
				Log.info(wrap(format("Standard image:'%s' exist,combined image will be saved for comparison", standardImageUrl())))
				//combine screenshot with standard image side by side,then tester can examine difference
				BufferedImage combined=ImageUtil.combine(standardImage(),mainUIScreenshot())
				//save combined image
				ImageIO.write(combined, "png", screenshotSavingFile())
			}else{
				Log.info(wrap(format("Standard image:'%s' not exist,only screeshot will be saved for comparison", standardImageUrl())))
				//save screenshot
				FileUtils.copyFile(mainUIScreenshot(), screenshotSavingFile())
			}
		}

		return true
	}

	private File screenshotSavingFile(){
		return new File(screenshotsDir() + testCase() + "-" + formatTime("HH-mm-ss") + ".png")
	}

	//NP path separator from library
	File standardImage(){
		return new File(standardImageUrl())
	}

	String standardImageUrl(){
		String pkg=this.getClass().getPackage().getName().replaceAll("\\.","/")

		return standardImagePath(pkg+"/"+testCase()+".png")
	}

	private File mainUIScreenshot(){
		//NP "MainUI" is hard coded
		return browser.screenshotOf(browser.findElemById("MainUI"))
	}

	def cleanupSpec() {
		browser.close()
	}

	String formatTime(String format) {
		return new SimpleDateFormat(format).format(new Date())
	}
}