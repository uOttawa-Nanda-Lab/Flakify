@Test public void should_map_rows_with_set() throws Exception {
  Set<String> followers=new HashSet<String>();
  def1=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","followers",DataType.set(DataType.text()));
  columnDefs=ColumnDefinitionBuilder.buildColumnDefinitions(def1);
  when(row.getColumnDefinitions()).thenReturn(columnDefs);
  when(row.getSet("followers",String.class)).thenReturn(followers);
  List<TypedMap> result=mapper.mapRows(Arrays.asList(row));
  assertThat(result).hasSize(1);
  TypedMap line=result.get(0);
  assertThat(line).hasSize(1);
  assertThat(line.get("followers")).isSameAs(followers);
}
