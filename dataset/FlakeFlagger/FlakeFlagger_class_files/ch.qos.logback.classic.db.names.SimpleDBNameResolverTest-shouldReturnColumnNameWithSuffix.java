/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.classic.db.names;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-03-22
 */
public class SimpleDBNameResolverTest {

  private SimpleDBNameResolver nameResolver;

  @Before
  public void setUp() throws Exception {
    nameResolver = new SimpleDBNameResolver();
    /*nameResolver.setTableNameSuffix("_ts");
    nameResolver.setColumnNamePrefix("cp_");
    nameResolver.setColumnNameSuffix("_cs");*/
  }

  @Test public void shouldReturnColumnNameWithSuffix() throws Exception{nameResolver.setColumnNameSuffix("_cs");assertThat(nameResolver.getTableName(TableName.LOGGING_EVENT)).isEqualTo("logging_event");assertThat(nameResolver.getColumnName(ColumnName.THREAD_NAME)).isEqualTo("thread_name_cs");}

}
