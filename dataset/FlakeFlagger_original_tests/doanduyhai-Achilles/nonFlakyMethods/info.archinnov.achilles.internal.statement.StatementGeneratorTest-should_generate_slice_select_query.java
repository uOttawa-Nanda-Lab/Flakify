@Test public void should_generate_slice_select_query() throws Exception {
  EntityMeta meta=prepareEntityMeta("id","comp1","comp2");
  when(sliceQuery.getMeta()).thenReturn(meta);
  when(sliceQuery.getCQLOrdering()).thenReturn(QueryBuilder.desc("comp1"));
  when(sliceQuery.getConsistencyLevel()).thenReturn(com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM);
  when(sliceQueryGenerator.generateWhereClauseForSelectSliceQuery(eq(sliceQuery),selectCaptor.capture())).thenReturn(statementWrapper);
  when(sliceQuery.getLimit()).thenReturn(98);
  when(sliceQuery.getBatchSize()).thenReturn(101);
  RegularStatementWrapper actual=generator.generateSelectSliceQuery(sliceQuery);
  assertThat(actual).isSameAs(statementWrapper);
  assertThat(selectCaptor.getValue().getQueryString()).isEqualTo("SELECT id,comp1,comp2,age,name,label FROM table ORDER BY comp1 DESC LIMIT 98;");
  assertThat(selectCaptor.getValue().getFetchSize()).isEqualTo(101);
}
