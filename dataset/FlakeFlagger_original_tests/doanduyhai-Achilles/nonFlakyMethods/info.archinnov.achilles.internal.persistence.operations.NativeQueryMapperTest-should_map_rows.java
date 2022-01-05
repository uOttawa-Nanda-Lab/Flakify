@Test public void should_map_rows() throws Exception {
  Long id=RandomUtils.nextLong();
  String name="name";
  def1=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","id",DataType.bigint());
  def2=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","name",DataType.text());
  columnDefs=ColumnDefinitionBuilder.buildColumnDefinitions(def1,def2);
  when(row.getColumnDefinitions()).thenReturn(columnDefs);
  when(cqlRowInvoker.invokeOnRowForType(row,Long.class,"id")).thenReturn(id);
  when(cqlRowInvoker.invokeOnRowForType(row,String.class,"name")).thenReturn(name);
  List<TypedMap> result=mapper.mapRows(Arrays.asList(row));
  verify(cqlRowInvoker).invokeOnRowForType(row,Long.class,"id");
  verify(cqlRowInvoker).invokeOnRowForType(row,String.class,"name");
  assertThat(result).hasSize(1);
  TypedMap line=result.get(0);
  assertThat(line).hasSize(2);
  assertThat(line.get("id")).isEqualTo(id);
  assertThat(line.get("name")).isEqualTo(name);
}
