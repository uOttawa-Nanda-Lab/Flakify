@Test public void should_fail_if_static_column_in_non_clustered_table() throws Exception {
  exception.expect(IllegalStateException.class);
  exception.expectMessage("The table 'test' cannot declare static columns '[stat]' without clustering columns");
  SchemaBuilder.createTable("test").addPartitionKey("pk",DataType.bigint()).addStaticColumn("stat",DataType.text()).build();
}
