@Test public void should_rename_column() throws Exception {
  final String built=SchemaBuilder.alterTable("test").renameColumn("name").to("description");
  assertThat(built).isEqualTo("\n\tALTER TABLE test RENAME name TO description");
}
