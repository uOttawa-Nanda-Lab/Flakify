@Test public void should_create_table_with_composite_partition_key_and_clustering_keys() throws Exception {
  final String built=SchemaBuilder.createTable("test").addPartitionKey("id1",DataType.bigint()).addPartitionKey("id2",DataType.text()).addClusteringKey("col1",DataType.uuid()).addClusteringKey("col2",DataType.uuid()).addColumn("name",DataType.text()).build();
  assertThat(built).isEqualTo("\n\tCREATE TABLE test(\n\t\t" + "id1 bigint,\n\t\t" + "id2 text,\n\t\t"+ "col1 uuid,\n\t\t"+ "col2 uuid,\n\t\t"+ "name text,\n\t\t"+ "PRIMARY KEY((id1, id2), col1, col2))");
}
