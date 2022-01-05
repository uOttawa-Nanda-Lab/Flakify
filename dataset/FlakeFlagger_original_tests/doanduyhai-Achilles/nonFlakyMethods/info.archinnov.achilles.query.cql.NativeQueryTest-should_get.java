@Test public void should_get() throws Exception {
  List<Row> rows=Arrays.asList(row);
  when(daoContext.execute(any(SimpleStatementWrapper.class)).all()).thenReturn(rows);
  List<TypedMap> result=new ArrayList<>();
  when(mapper.mapRows(rows)).thenReturn(result);
  List<TypedMap> actual=query.get();
  assertThat(actual).isSameAs(result);
}
