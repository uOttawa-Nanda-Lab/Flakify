@Test public void should_return_null_when_no_row() throws Exception {
  List<Row> rows=Arrays.asList(row);
  when(daoContext.execute(any(SimpleStatementWrapper.class)).all()).thenReturn(rows);
  List<TypedMap> result=new ArrayList<>();
  when(mapper.mapRows(rows)).thenReturn(result);
  Map<String,Object> actual=query.first();
  assertThat(actual).isNull();
}
