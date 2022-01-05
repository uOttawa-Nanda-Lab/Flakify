@Test public void should_create_simple_table_with_keyspace() throws Exception {
  final String built=SchemaBuilder.createTable("ks","test").addPartitionKey("id",DataType.bigint()).addColumn("name",DataType.text()).build();
  assertThat(built).isEqualTo("\n\tCREATE TABLE ks.test(\n\t\t" + "id bigint,\n\t\t" + "name text,\n\t\t"+ "PRIMARY KEY(id))");
}
