@Test public void should_fail_if_partition_key_is_a_reserved_keyword() throws Exception {
  exception.expect(IllegalArgumentException.class);
  exception.expectMessage("The partition key name 'add' is not allowed because it is a reserved keyword");
  SchemaBuilder.createTable("test").addPartitionKey("add",DataType.bigint()).addColumn("col",DataType.text()).build();
}
