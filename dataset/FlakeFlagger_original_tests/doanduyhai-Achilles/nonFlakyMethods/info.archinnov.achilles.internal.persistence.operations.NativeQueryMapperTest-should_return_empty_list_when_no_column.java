@Test public void should_return_empty_list_when_no_column() throws Exception {
  def1=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","id",DataType.bigint());
  when(row.getColumnDefinitions()).thenReturn(null);
  List<TypedMap> result=mapper.mapRows(Arrays.asList(row));
  assertThat(result).isEmpty();
  verifyZeroInteractions(cqlRowInvoker);
}
