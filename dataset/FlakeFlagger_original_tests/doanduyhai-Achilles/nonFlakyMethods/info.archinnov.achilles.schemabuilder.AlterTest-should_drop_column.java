@Test public void should_drop_column() throws Exception {
  final String built=SchemaBuilder.alterTable("test").dropColumn("name");
  assertThat(built).isEqualTo("\n\tALTER TABLE test DROP name");
}
