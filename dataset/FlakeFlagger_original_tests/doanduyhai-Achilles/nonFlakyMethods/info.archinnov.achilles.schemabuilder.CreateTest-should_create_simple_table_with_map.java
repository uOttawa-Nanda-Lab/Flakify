@Test public void should_create_simple_table_with_map() throws Exception {
  final String built=SchemaBuilder.createTable("test").addPartitionKey("id",DataType.bigint()).addColumn("friends",DataType.map(DataType.cint(),DataType.text())).build();
  assertThat(built).isEqualTo("\n\tCREATE TABLE test(\n\t\t" + "id bigint,\n\t\t" + "friends map<int,text>,\n\t\t"+ "PRIMARY KEY(id))");
}
