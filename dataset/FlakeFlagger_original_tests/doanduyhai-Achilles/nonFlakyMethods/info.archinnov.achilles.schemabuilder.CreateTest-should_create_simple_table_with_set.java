@Test public void should_create_simple_table_with_set() throws Exception {
  final String built=SchemaBuilder.createTable("test").addPartitionKey("id",DataType.bigint()).addColumn("friends",DataType.set(DataType.text())).build();
  assertThat(built).isEqualTo("\n\tCREATE TABLE test(\n\t\t" + "id bigint,\n\t\t" + "friends set<text>,\n\t\t"+ "PRIMARY KEY(id))");
}
