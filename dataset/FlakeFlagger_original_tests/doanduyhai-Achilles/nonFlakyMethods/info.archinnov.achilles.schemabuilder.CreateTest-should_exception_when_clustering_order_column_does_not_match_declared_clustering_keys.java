@Test(expected=IllegalArgumentException.class) public void should_exception_when_clustering_order_column_does_not_match_declared_clustering_keys() throws Exception {
  SchemaBuilder.createTable("test").addPartitionKey("id",DataType.bigint()).addClusteringKey("col1",DataType.uuid()).addClusteringKey("col2",DataType.uuid()).addColumn("name",DataType.text()).withOptions().clusteringOrder(new Create.Options.ClusteringOrder("col3",ASC)).build();
}
