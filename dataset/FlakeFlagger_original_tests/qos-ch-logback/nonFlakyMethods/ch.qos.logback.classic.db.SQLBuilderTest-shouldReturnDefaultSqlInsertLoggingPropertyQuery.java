@Test public void shouldReturnDefaultSqlInsertLoggingPropertyQuery() throws Exception {
  DBNameResolver nameResolver=new DefaultDBNameResolver();
  String sql=SQLBuilder.buildInsertPropertiesSQL(nameResolver);
  final String expected="INSERT INTO logging_event_property (event_id, mapped_key, mapped_value) VALUES (?, ?, ?)";
  assertThat(sql).isEqualTo(expected);
}
