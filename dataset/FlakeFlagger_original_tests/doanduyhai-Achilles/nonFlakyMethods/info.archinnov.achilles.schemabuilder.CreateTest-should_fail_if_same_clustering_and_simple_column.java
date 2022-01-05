@Test public void should_fail_if_same_clustering_and_simple_column() throws Exception {
  exception.expect(IllegalStateException.class);
  exception.expectMessage("The '[cluster]' columns can not be declared as clustering keys and simple columns at the same time");
  SchemaBuilder.createTable("test").addPartitionKey("pk",DataType.bigint()).addClusteringKey("cluster",DataType.bigint()).addColumn("cluster",DataType.text()).build();
}
