/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api.base;

import static com.dsc.util.Util.nullOrEmpty;
import static com.dsc.util.Util.stringfy;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author alex
 * @CreateTime 02.03.2017 13:37:57
 * @Version 1.0
 * @Since 1.0
 */
public class Test
{
	private static final String	HTTP	= "http";
	private static final String	WWW		= "http";

	private static HttpMethod method(List<Object> fields, int method)
	{
		if (obj(fields, method) == null)
		{
			return null;
		}
		return HttpMethod.valueOf(str(fields, method));
	}

	/**
	 * @param fields
	 * @param idx
	 */
	private static Object obj(List<Object> fields, int idx)
	{
		if (idx >= fields.size())
		{
			// HP
			throw new IllegalStateException(String.format("", idx));
		}

		return fields.get(idx);
	}

	private static String str(List<Object> fields, int idx)
	{
		if (obj(fields, idx) == null)
		{
			return null;
		}

		return (String) fields.get(idx);
	}

	public String		caseName;
	public Object		data;
	public String		expectation;
	public HttpMethod	method;
	public String		result;
	public String		url;
	private String		domain;
	private int			port;

	/**
	 * @param fields
	 * @param column
	 * @param domain
	 * @param port
	 */
	public Test(List<Object> fields, ColumnCfg column, String domain, int port)
	{
		this(str(fields, column.caseName), str(fields, column.url), method(fields, column.method), obj(fields, column.data),
				domain, port);
	}

	/**
	 * @param caseName
	 * @param data
	 * @param method
	 * @param url
	 * @param domain
	 * @param port
	 */
	public Test(String caseName, String url, HttpMethod method, Object data, String domain, int port)
	{
		this(caseName, url, method, data, null, domain, port);
	}

	/**
	 * @param caseName
	 * @param data
	 * @param expectation
	 * @param method
	 * @param url
	 * @param domain
	 * @param port
	 */
	public Test(String caseName, String url, HttpMethod method, Object data, String expectation, String domain, int port)
	{
		this.caseName = caseName;
		this.expectation = expectation;
		this.method = method;
		this.domain = domain;
		this.port = port;
		setUpUrl(url);
		setUpData(data);
	}

	public String diff()
	{
		return StringUtils.difference(expectation, result);
	}

	/**
	 * @return
	 */
	public String invalidField()
	{
		if (nullOrEmpty(url))
		{
			return "url";
		}

		if (method == null)
		{
			return "method";
		}

		// if(nullOrEmpty())
		// {
		// return "";
		// }

		// if(nullOrEmpty())
		// {
		// return "";
		// }

		return null;
	}

	/**
	 * @param error
	 */
	public void setResult(String result)
	{
		this.result = result;
	}

	/**
	 * @return
	 */
	public String[] stringfyFields()
	{
		return new String[] { caseName, url, method.toString(), stringfy(data), expectation, result, diff() };
	}

	/**
	 * @param data
	 */
	private void setUpData(Object data)
	{
		//		String[] params=
	}

	/**
	 * @return
	 */
	private void setUpUrl(String url)
	{
		if (url.startsWith(HTTP) || url.startsWith(WWW) || url.startsWith(domain))
		{
			this.url = url;
		}

		this.url = domain + ":" + port;
	}
}
