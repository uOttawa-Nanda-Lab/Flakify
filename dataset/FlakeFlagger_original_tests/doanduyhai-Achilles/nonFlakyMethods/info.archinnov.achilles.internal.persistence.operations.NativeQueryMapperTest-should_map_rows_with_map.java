@Test public void should_map_rows_with_map() throws Exception {
  Map<BigInteger,String> preferences=new HashMap<BigInteger,String>();
  def1=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","preferences",DataType.map(DataType.varint(),DataType.text()));
  columnDefs=ColumnDefinitionBuilder.buildColumnDefinitions(def1);
  when(row.getColumnDefinitions()).thenReturn(columnDefs);
  when(row.getMap("preferences",BigInteger.class,String.class)).thenReturn(preferences);
  List<TypedMap> result=mapper.mapRows(Arrays.asList(row));
  assertThat(result).hasSize(1);
  TypedMap line=result.get(0);
  assertThat(line).hasSize(1);
  assertThat(line.get("preferences")).isSameAs(preferences);
}
