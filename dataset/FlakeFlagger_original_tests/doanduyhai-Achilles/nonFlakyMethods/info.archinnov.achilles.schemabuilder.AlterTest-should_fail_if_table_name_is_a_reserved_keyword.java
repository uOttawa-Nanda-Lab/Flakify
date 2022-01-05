@Test public void should_fail_if_table_name_is_a_reserved_keyword() throws Exception {
  exception.expect(IllegalArgumentException.class);
  exception.expectMessage("The table name 'add' is not allowed because it is a reserved keyword");
  SchemaBuilder.alterTable("add").addColumn("test").type(DataType.ascii());
}
