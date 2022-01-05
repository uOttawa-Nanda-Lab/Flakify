@Test public void should_fail_if_renamed_column_is_a_reserved_keyword() throws Exception {
  exception.expect(IllegalArgumentException.class);
  exception.expectMessage("The renamed column name 'add' is not allowed because it is a reserved keyword");
  SchemaBuilder.alterTable("test").renameColumn("add");
}
