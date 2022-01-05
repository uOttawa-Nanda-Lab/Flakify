@Test public void should_fail_if_same_simple_and_static_column() throws Exception {
  exception.expect(IllegalStateException.class);
  exception.expectMessage("The '[col]' columns can not be declared as simple columns and static columns at the same time");
  SchemaBuilder.createTable("test").addPartitionKey("pk",DataType.bigint()).addClusteringKey("cluster",DataType.uuid()).addColumn("col",DataType.bigint()).addStaticColumn("col",DataType.text()).build();
}
