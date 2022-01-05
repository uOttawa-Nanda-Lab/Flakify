@Test public void should_generate_slice_delete_query() throws Exception {
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  when(sliceQuery.getMeta()).thenReturn(meta);
  when(sliceQueryGenerator.generateWhereClauseForDeleteSliceQuery(eq(sliceQuery),deleteCaptor.capture())).thenReturn(statementWrapper);
  RegularStatementWrapper actual=generator.generateRemoveSliceQuery(sliceQuery);
  assertThat(actual).isSameAs(statementWrapper);
  assertThat(deleteCaptor.getValue().getQueryString()).isEqualTo("DELETE  FROM table;");
}
