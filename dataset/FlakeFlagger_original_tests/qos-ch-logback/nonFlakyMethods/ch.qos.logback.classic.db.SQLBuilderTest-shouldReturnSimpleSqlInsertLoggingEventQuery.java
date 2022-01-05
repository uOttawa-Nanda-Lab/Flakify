@Test public void shouldReturnSimpleSqlInsertLoggingEventQuery() throws Exception {
  DBNameResolver nameResolver=createSimpleDBNameResolver();
  String sql=SQLBuilder.buildInsertSQL(nameResolver);
  final String expected="INSERT INTO tp_logging_event_ts (cp_timestmp_cs, cp_formatted_message_cs, cp_logger_name_cs, cp_level_string_cs, cp_thread_name_cs, cp_reference_flag_cs, cp_arg0_cs, cp_arg1_cs, cp_arg2_cs, cp_arg3_cs, cp_caller_filename_cs, cp_caller_class_cs, cp_caller_method_cs, cp_caller_line_cs) VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  assertThat(sql).isEqualTo(expected);
}
