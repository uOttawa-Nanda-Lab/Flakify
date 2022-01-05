@Test public void testGetLoggingEventPropertyColumnName() throws Exception {
  String columnName=resolver.getColumnName(ColumnName.MAPPED_KEY);
  assertThat(columnName).isEqualTo("mapped_key");
}
