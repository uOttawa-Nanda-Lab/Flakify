@Test public void should_fail_if_static_column_is_a_reserved_keyword() throws Exception {
  exception.expect(IllegalArgumentException.class);
  exception.expectMessage("The static column name 'add' is not allowed because it is a reserved keyword");
  SchemaBuilder.createTable("test").addPartitionKey("pk",DataType.bigint()).addClusteringKey("cluster",DataType.uuid()).addStaticColumn("add",DataType.text()).addColumn("col",DataType.text()).build();
}
