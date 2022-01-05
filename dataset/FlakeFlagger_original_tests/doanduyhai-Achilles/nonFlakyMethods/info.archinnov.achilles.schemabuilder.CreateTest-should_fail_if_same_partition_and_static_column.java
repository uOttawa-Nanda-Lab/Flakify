@Test public void should_fail_if_same_partition_and_static_column() throws Exception {
  exception.expect(IllegalStateException.class);
  exception.expectMessage("The '[pk]' columns can not be declared as partition keys and static columns at the same time");
  SchemaBuilder.createTable("test").addPartitionKey("pk",DataType.bigint()).addStaticColumn("pk",DataType.text()).build();
}
