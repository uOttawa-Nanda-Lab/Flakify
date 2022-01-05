@Test public void should_fail_creating_table_with_static_columns_and_compact_storage() throws Exception {
  exception.expect(IllegalStateException.class);
  exception.expectMessage("Cannot create table 'test' with compact storage and static columns '[stat]'");
  SchemaBuilder.createTable("test").addPartitionKey("pk",DataType.bigint()).addClusteringKey("cluster",DataType.uuid()).addStaticColumn("stat",DataType.text()).withOptions().compactStorage(true).build();
}
