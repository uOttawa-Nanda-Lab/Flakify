@Test public void should_generate_slice_select_query_without_ordering() throws Exception {
  EntityMeta meta=prepareEntityMeta("id","comp1","comp2");
  when(sliceQuery.getMeta()).thenReturn(meta);
  when(sliceQuery.getCQLOrdering()).thenReturn(null);
  when(sliceQuery.getConsistencyLevel()).thenReturn(com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM);
  when(sliceQueryGenerator.generateWhereClauseForSelectSliceQuery(eq(sliceQuery),selectCaptor.capture())).thenReturn(statementWrapper);
  when(sliceQuery.getLimit()).thenReturn(98);
  when(sliceQuery.getBatchSize()).thenReturn(101);
  RegularStatementWrapper actual=generator.generateSelectSliceQuery(sliceQuery);
  assertThat(actual).isSameAs(statementWrapper);
  assertThat(selectCaptor.getValue().getQueryString()).isEqualTo("SELECT id,comp1,comp2,age,name,label FROM table LIMIT 98;");
  assertThat(selectCaptor.getValue().getFetchSize()).isEqualTo(101);
}
