@Test public void should_fail_if_same_partition_and_simple_column() throws Exception {
  exception.expect(IllegalStateException.class);
  exception.expectMessage("The '[pk]' columns can not be declared as partition keys and simple columns at the same time");
  SchemaBuilder.createTable("test").addPartitionKey("pk",DataType.bigint()).addColumn("pk",DataType.text()).build();
}
