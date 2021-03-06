/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db.sql;

import static com.dsc.util.Util.wrap;
import static java.lang.String.format;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.dsc.test.db.Column;
import com.dsc.test.db.Columns;
import com.dsc.test.db.DataBase;
import com.dsc.test.db.Table;
import com.dsc.test.db.sql.ChildTable.Info;
import com.dsc.util.Log;
import com.dsc.util.Util;
import com.google.common.collect.Lists;

/**
 * @Author alex
 * @CreateTime Aug 19, 2016 5:34:55 PM
 * @Version 1.0
 * @Since 1.0
 */
public class SQLTableImpl implements Table
{
	private DataBase	dataBase;
	private String		primaryKey	= "id_in_source";

	private Schema		schema;

	/**
	 * @param dataBase
	 */
	public SQLTableImpl(DataBase dataBase, Schema schema)
	{
		Util.mustNotNull(this.dataBase = dataBase, "dataBase");
		Util.mustNotNull(this.schema = schema, "schema");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#charTypedColumns()
	 */
	@Override
	public Columns charTypedColumns()
	{
		Columns columns = new Columns(dataBase, this);

		for (Column column : columns())
		{
			if (column.isCharTyped())
			{
				columns.add(column);
			}
		}

		return columns.isEmpty() ? null : columns;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#ensureColumn(java.lang.String)
	 */
	@Override
	public Column column(String name)
	{
		return new Column(dataBase, schema.column(name), this);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#columns()
	 */
	@Override
	public Columns columns()
	{
		Columns columns = new Columns(dataBase, this);

		for (schemacrawler.schema.Column column : schema().getColumns())
		{
			columns.add(new Column(dataBase, schema.column(column), this));
		}

		return columns;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#columns(java.lang.String[])
	 */
	@Override
	public Columns columns(String... columns)
	{
		Columns cls = new Columns(dataBase, this);

		for (String cl : columns)
		{
			cls.add(new Column(dataBase, schema.column(cl), this));
		}

		return cls;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#columnsExcept(java.lang.String[])
	 */
	@Override
	public Columns columnsExcept(String... columns)
	{
		try (ResultSet rs = dataBase.query("SELECT * FROM " + schema.table());)
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			// The column count starts from 1
			List<String> excludeds = Lists.newArrayList(columns);
			List<String> remains = Lists.newArrayList();
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
			{
				if (!excludeds.contains(rsmd.getColumnName(i)))
				{
					remains.add(rsmd.getColumnName(i));
				}
			}

			return columns(remains.toArray(new String[remains.size()]));
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#count()
	 */
	@Override
	public int count()
	{
		try (ResultSet rs = dataBase.query(format("SELECT COUNT(*) FROM %s", name())))
		{
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#deleteAll()
	 */
	@Override
	public int deleteAll()
	{
		return deleteBy(format("DELETE FROM %s", name()));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#deleteBy(java.lang.String)
	 */
	@Override
	public int deleteBy(String sql)
	{
		Util.mustNotNull(sql, "sql");

		dataBase.ensureConnected();

		return dataBase.exec(sql);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#deleteBy(java.lang.String, java.lang.Object)
	 */
	@Override
	public int deleteBy(String column, Object cellValue)
	{
		Util.mustNotNullOrEmpty(column, "column name");
		Util.mustNotNull(cellValue, "cellValue");

		return deleteBy(format("DELETE FROM %s %s", name(), whereColumnEquals(column, cellValue)));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.table#deleteRowById(java.lang.Object)
	 */
	@Override
	public int deleteById(Object id)
	{
		Util.mustNotNull(id, "id");

		return deleteBy("id", id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#deleteCascadely(java.lang.String,
	 * java.lang.Object,
	 * java.util.List)
	 */
	@Override
	public int deleteCascadely(String column, Object cellValue, List<Info> childTablesInfo)
	{
		// delete cascaded records in child tables
		for (Info child : childTablesInfo)
		{
			dataBase.exec(
					format("DELETE FROM %s WHERE %s IN (SELECT parent.%s FROM %s AS parent INNER JOIN (SELECT * FROM %s) AS child ON parent.%s=child.%s %s)",
							child.table, child.foreignKey, child.refereeColumn, name(), child.table, child.refereeColumn,
							child.foreignKey, whereColumnEquals("parent." + column, cellValue)));
		}

		// delete records in parent table
		return deleteBy(column, cellValue);
	}

	@Override
	public void ensureSchemaRetrieved()
	{
		schema.ensureTableRetrieved();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#existedBy(java.lang.String)
	 */
	@Override
	public boolean existedBy(String query)
	{
		Util.mustNotNull(query, "query");

		try
		{
			return selectBy(query).next();
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#existedBy(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean existedBy(String column, Object cellValue)
	{
		Util.mustNotNullOrEmpty(column, "column name");
		Util.mustNotNull(cellValue, "cellValue");

		return existedBy(String.format("SELECT * FROM %s %s", name(), whereColumnEquals(column, cellValue)));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#existedById(java.lang.Object)
	 */
	@Override
	public boolean existedById(Object id)
	{
		Util.mustNotNull(id, "id");

		return existedBy("id", id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#hasReferenceColumns()
	 */
	@Override
	public Columns hasReferenceColumns()
	{
		Columns columns = new Columns(dataBase, this);

		for (Column column : columns())
		{
			if (column.hasReferences())
			{
				columns.add(column);
			}
		}

		return columns.isEmpty() ? null : columns;
	}

	@Override
	public void insert(String sql, Object[][] values)
	{
		dataBase.ensureConnected();

		Log.debug(format("Batching insertion:-----------%s\n", sql));
		Log.debug(format("------------------------------%s\n", flatten(values)));

		try (PreparedStatement stmt = dataBase.prepareStatement(sql))
		{
			for (Object[] tupe : values)
			{
				for (int i = 0; i < tupe.length; i++)
				{
					Object value = tupe[i];
					int l = i + 1;

					if (value instanceof Double)
					{
						stmt.setDouble(l, (double) value);
					} else if (value instanceof Float)
					{
						stmt.setFloat(l, (float) value);
					} else if (value instanceof Integer)
					{
						stmt.setInt(l, (int) value);
					} else if (value instanceof Long)
					{
						stmt.setLong(l, (long) value);
					} else if (value instanceof String)
					{
						stmt.setString(l, (String) value);
					} else if (value instanceof Timestamp)
					{
						stmt.setTimestamp(l, (Timestamp) value);
					} else
					{
						throw new RuntimeException(value + " hasn't be handled for batch insertion statement preparing");
					}
				}

				//
				stmt.addBatch();
			}

			stmt.executeBatch();

		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#name()
	 */
	@Override
	public String name()
	{
		return schema.table();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#nullifyCell(java.lang.Object,
	 * java.lang.String, java.lang.Object[])
	 */
	@Override
	public int nullifyCell(String targetCol, String filterCol, Object filterValue, Object... defaultValue)
	{
		Util.mustNotNullOrEmpty(targetCol, "targetCol");
		Util.mustNotNullOrEmpty(filterCol, "filterCol");
		Util.mustNotNull(filterValue, "filterValue");

		dataBase.ensureConnected();
		try
		{
			return dataBase
					.exec(format("UPDATE %s SET %s = NULL %s", name(), targetCol, whereColumnEquals(filterCol, filterValue)));
		} catch (Exception e)// column is NOT NULL in DDL
		{
			Object value = defaultValue == null ? column(targetCol).defaultValue() : defaultValue[0];

			return dataBase.exec(
					format("UPDATE %s SET %s %s", name(), targetCol + "=" + value, whereColumnEquals(filterCol, filterValue)));
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#nullifyCell(java.lang.Object,
	 * java.lang.String[])
	 */
	// @Override
	// public int nullifyCell(Object id, String... columns)
	// {
	// Util.mustNotNull(id, "id");
	// Util.mustNotNull(columns, "columns");
	//
	// for (String col : columns)
	// {
	// nullifyCell(id, col);
	// }
	//
	// return 1;
	// }

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#nullifyCellLite(java.lang.String,
	 * java.lang.Object, java.lang.Object[])
	 */
	@Override
	public int nullifyCellLite(String filterCol, Object filterValue, Object... defaultValue)
	{
		return nullifyCell(filterCol, filterCol, filterValue, defaultValue);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#primaryKey()
	 */
	@Override
	public String primaryKey()
	{
		return primaryKey;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#primaryKey(java.lang.String)
	 */
	@Override
	public Table primaryKey(String primaryKey)
	{
		this.primaryKey = primaryKey;
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#selectBy(java.lang.String)
	 */
	@Override
	public ResultSet selectBy(String sql)
	{
		Util.mustNotNull(sql, "sql");

		dataBase.ensureConnected();

		return dataBase.query(sql);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#selectBy(java.lang.String, java.lang.Object)
	 */
	@Override
	public ResultSet selectBy(String column, Object cellValue)
	{
		Util.mustNotNullOrEmpty(column, "column name");
		Util.mustNotNull(cellValue, "cellValue");

		return selectBy(format("SELECT * FROM %s %s", name(), whereColumnEquals(column, cellValue)));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#selectBy(java.lang.String, java.lang.Object,
	 * java.lang.String)
	 */
	@Override
	public Object selectBy(String filterColumn, Object filterValue, String resultColumn)
	{
		Util.mustNotNullOrEmpty(filterColumn, "filter name");
		Util.mustNotNull(filterValue, "filter value");
		Util.mustNotNull(resultColumn, "result column");

		try (ResultSet res = selectBy(
				format("SELECT %s FROM %s %s", resultColumn, name(), whereColumnEquals(filterColumn, filterValue))))
		{
			List<Object> list = Lists.newArrayList();

			while (res.next())
			{
				list.add(res.getObject(1));
			}

			if (list.size() == 1)
			{
				return list.get(0);
			} else if (list.size() > 1)
			{
				return list;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#selectById(java.lang.Object)
	 */
	@Override
	public ResultSet selectById(Object id)
	{
		Util.mustNotNull(id, "id");

		return selectBy("id", id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.Table#selectById(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object selectById(Object id, String column)
	{
		Util.mustNotNull(id, "id");
		Util.mustNotNullOrEmpty(column, "column name");

		try (ResultSet res = selectBy(String.format("SELECT %s FROM %s %s", column, name(), whereColumnEquals("id", id))))
		{
			if (res.next())
			{
				return res.getObject(1);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	// HP override other methods
	@Override
	public String toString()
	{
		return name();
	}

	private String flatten(Object[][] arr)
	{
		String flattened = "";
		for (Object[] subArr : arr)
		{
			flattened = flattened + "[";
			for (Object val : subArr)
			{
				String item = val == null ? "null" : val.toString();
				flattened = flattened + item + ",  ";
			}
			flattened = flattened.substring(0, flattened.length() - 3) + "]\n";
		}

		return flattened;
	}

	/**
	 * @return
	 */
	private schemacrawler.schema.Table schema()
	{
		return schema.ensureTableRetrieved();
	}

	private String whereColumnEquals(String column, Object value)
	{
		String whereIdEquals = "WHERE " + column + "=";

		if (value instanceof Integer)
		{
			return whereIdEquals + (int) value;
		} else if (value instanceof Long)
		{
			return whereIdEquals + (long) value;
		} else if (value instanceof String)
		{
			return whereIdEquals + "'" + (String) value + "'";
		} else
		{
			throw new RuntimeException(
					wrap("%s's type is %s that isn't a supported type:int/Integer,long/Long,String typed id supported,",
							value.toString(), value.getClass().getSimpleName()));
		}
	}
	//
	// private String whereIdEquals(Object id)
	// {
	// return whereColumnEquals("id", id);
	// }
}