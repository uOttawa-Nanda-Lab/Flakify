@Test public void should_get_clustered_entities() throws Exception {
  RegularStatementWrapper regularWrapper=mock(RegularStatementWrapper.class);
  Row row=mock(Row.class);
  List<Row> rows=Arrays.asList(row);
  when(generator.generateSelectSliceQuery(anySliceQuery())).thenReturn(regularWrapper);
  when(daoContext.execute(regularWrapper).all()).thenReturn(rows);
  when(meta.instanciate()).thenReturn(entity);
  when(contextFactory.newContext(entity)).thenReturn(context);
  when(proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,entityFacade)).thenReturn(entity);
  List<ClusteredEntity> actual=executor.get(sliceQuery);
  assertThat(actual).containsOnly(entity);
  verify(meta).intercept(entity,Event.POST_LOAD);
  verify(mapper).setNonCounterPropertiesToEntity(row,meta,entity);
}
