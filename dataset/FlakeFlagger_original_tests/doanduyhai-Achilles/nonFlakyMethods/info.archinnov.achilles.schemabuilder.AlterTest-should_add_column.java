@Test public void should_add_column() throws Exception {
  final String built=SchemaBuilder.alterTable("test").addColumn("location").type(DataType.ascii());
  assertThat(built).isEqualTo("\n\tALTER TABLE test ADD location ascii");
}
