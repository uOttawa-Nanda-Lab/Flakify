@Test public void should_create_simple_table() throws Exception {
  final String built=SchemaBuilder.createTable("test").addPartitionKey("id",DataType.bigint()).addColumn("name",DataType.text()).build();
  assertThat(built).isEqualTo("\n\tCREATE TABLE test(\n\t\t" + "id bigint,\n\t\t" + "name text,\n\t\t"+ "PRIMARY KEY(id))");
}
