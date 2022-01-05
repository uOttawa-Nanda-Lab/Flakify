@Test public void should_alter_column_type() throws Exception {
  final String built=SchemaBuilder.alterTable("test").alterColumn("name").type(DataType.ascii());
  assertThat(built).isEqualTo("\n\tALTER TABLE test ALTER name TYPE ascii");
}
