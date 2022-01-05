@Test(expected=IllegalArgumentException.class) public void should_exception_when_blank_clustering_order_column_provided() throws Exception {
  SchemaBuilder.createTable("test").addPartitionKey("id",DataType.bigint()).addClusteringKey("col1",DataType.uuid()).addClusteringKey("col2",DataType.uuid()).addColumn("name",DataType.text()).withOptions().clusteringOrder(new Create.Options.ClusteringOrder("",DESC)).build();
}
