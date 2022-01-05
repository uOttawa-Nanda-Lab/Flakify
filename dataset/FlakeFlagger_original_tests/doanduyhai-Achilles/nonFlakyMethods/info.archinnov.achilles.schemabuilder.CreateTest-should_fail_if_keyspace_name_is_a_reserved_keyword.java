@Test public void should_fail_if_keyspace_name_is_a_reserved_keyword() throws Exception {
  exception.expect(IllegalArgumentException.class);
  exception.expectMessage("The keyspace name 'add' is not allowed because it is a reserved keyword");
  SchemaBuilder.createTable("add","test").addPartitionKey("pk",DataType.bigint()).addColumn("col",DataType.text()).build();
}
