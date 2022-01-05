@Test public void should_get_one() throws Exception {
  List<Row> rows=Arrays.asList(row);
  when(daoContext.execute(any(SimpleStatementWrapper.class)).all()).thenReturn(rows);
  List<TypedMap> result=new ArrayList<>();
  TypedMap line=new TypedMap();
  result.add(line);
  when(mapper.mapRows(rows)).thenReturn(result);
  TypedMap actual=query.first();
  assertThat(actual).isSameAs(line);
}
