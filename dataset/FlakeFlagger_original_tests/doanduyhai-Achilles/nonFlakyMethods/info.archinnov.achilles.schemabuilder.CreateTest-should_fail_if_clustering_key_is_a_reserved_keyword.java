@Test public void should_fail_if_clustering_key_is_a_reserved_keyword() throws Exception {
  exception.expect(IllegalArgumentException.class);
  exception.expectMessage("The clustering key name 'add' is not allowed because it is a reserved keyword");
  SchemaBuilder.createTable("test").addPartitionKey("pk",DataType.bigint()).addClusteringKey("add",DataType.uuid()).addColumn("col",DataType.text()).build();
}
