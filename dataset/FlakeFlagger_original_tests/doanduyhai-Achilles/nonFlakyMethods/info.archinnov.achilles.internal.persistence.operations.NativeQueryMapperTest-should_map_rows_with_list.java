@Test public void should_map_rows_with_list() throws Exception {
  ArrayList<String> friends=new ArrayList<String>();
  def1=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","friends",DataType.list(DataType.text()));
  columnDefs=ColumnDefinitionBuilder.buildColumnDefinitions(def1);
  when(row.getColumnDefinitions()).thenReturn(columnDefs);
  when(row.getList("friends",String.class)).thenReturn(friends);
  List<TypedMap> result=mapper.mapRows(Arrays.asList(row));
  assertThat(result).hasSize(1);
  TypedMap line=result.get(0);
  assertThat(line).hasSize(1);
  assertThat(line.get("friends")).isSameAs(friends);
}
