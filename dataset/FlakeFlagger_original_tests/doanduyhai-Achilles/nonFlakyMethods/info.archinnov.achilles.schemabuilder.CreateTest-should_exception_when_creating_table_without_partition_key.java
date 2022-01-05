@Test(expected=IllegalStateException.class) public void should_exception_when_creating_table_without_partition_key() throws Exception {
  SchemaBuilder.createTable("test").addColumn("name",DataType.text()).build();
}
