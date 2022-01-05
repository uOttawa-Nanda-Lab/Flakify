@Test public void should_create_simple_table_with_list() throws Exception {
  final String built=SchemaBuilder.createTable("test").addPartitionKey("id",DataType.bigint()).addColumn("friends",DataType.list(DataType.text())).build();
  assertThat(built).isEqualTo("\n\tCREATE TABLE test(\n\t\t" + "id bigint,\n\t\t" + "friends list<text>,\n\t\t"+ "PRIMARY KEY(id))");
}
