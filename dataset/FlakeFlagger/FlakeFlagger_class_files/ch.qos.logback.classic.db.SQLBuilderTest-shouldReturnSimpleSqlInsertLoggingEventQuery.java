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
package ch.qos.logback.classic.db;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import ch.qos.logback.classic.db.names.DBNameResolver;
import ch.qos.logback.classic.db.names.DefaultDBNameResolver;
import ch.qos.logback.classic.db.names.SimpleDBNameResolver;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-03-22
 */
public class SQLBuilderTest {

  private DBNameResolver createSimpleDBNameResolver() {
    final SimpleDBNameResolver nameResolver = new SimpleDBNameResolver();
    nameResolver.setTableNamePrefix("tp_");
    nameResolver.setTableNameSuffix("_ts");
    nameResolver.setColumnNamePrefix("cp_");
    nameResolver.setColumnNameSuffix("_cs");
    return nameResolver;
  }

  @Test public void shouldReturnSimpleSqlInsertLoggingEventQuery() throws Exception{DBNameResolver nameResolver=createSimpleDBNameResolver();String sql=SQLBuilder.buildInsertSQL(nameResolver);final String expected="INSERT INTO tp_logging_event_ts (cp_timestmp_cs, cp_formatted_message_cs, cp_logger_name_cs, cp_level_string_cs, cp_thread_name_cs, cp_reference_flag_cs, cp_arg0_cs, cp_arg1_cs, cp_arg2_cs, cp_arg3_cs, cp_caller_filename_cs, cp_caller_class_cs, cp_caller_method_cs, cp_caller_line_cs) VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";assertThat(sql).isEqualTo(expected);}

}
