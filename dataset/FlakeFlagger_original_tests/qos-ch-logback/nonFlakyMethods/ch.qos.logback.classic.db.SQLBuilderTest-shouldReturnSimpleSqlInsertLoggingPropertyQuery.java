@Test public void shouldReturnSimpleSqlInsertLoggingPropertyQuery() throws Exception {
  DBNameResolver nameResolver=createSimpleDBNameResolver();
  String sql=SQLBuilder.buildInsertPropertiesSQL(nameResolver);
  final String expected="INSERT INTO tp_logging_event_property_ts (cp_event_id_cs, cp_mapped_key_cs, cp_mapped_value_cs) VALUES (?, ?, ?)";
  assertThat(sql).isEqualTo(expected);
}
