@Test public void should_alter_column_type_with_keyspace() throws Exception {
  final String built=SchemaBuilder.alterTable("ks","test").alterColumn("name").type(DataType.ascii());
  assertThat(built).isEqualTo("\n\tALTER TABLE ks.test ALTER name TYPE ascii");
}
