@Test public void should_create_simple_table_if_not_exists() throws Exception {
  final String built=SchemaBuilder.createTable("test").ifNotExists(true).addPartitionKey("id",DataType.bigint()).addColumn("name",DataType.text()).build();
  assertThat(built).isEqualTo("\n\tCREATE TABLE IF NOT EXISTS test(\n\t\t" + "id bigint,\n\t\t" + "name text,\n\t\t"+ "PRIMARY KEY(id))");
}
