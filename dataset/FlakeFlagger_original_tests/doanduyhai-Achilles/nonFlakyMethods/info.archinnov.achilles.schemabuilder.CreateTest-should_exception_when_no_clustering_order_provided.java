@Test(expected=IllegalArgumentException.class) public void should_exception_when_no_clustering_order_provided() throws Exception {
  SchemaBuilder.createTable("test").addPartitionKey("id",DataType.bigint()).addClusteringKey("col1",DataType.uuid()).addClusteringKey("col2",DataType.uuid()).addColumn("name",DataType.text()).withOptions().clusteringOrder().build();
}
