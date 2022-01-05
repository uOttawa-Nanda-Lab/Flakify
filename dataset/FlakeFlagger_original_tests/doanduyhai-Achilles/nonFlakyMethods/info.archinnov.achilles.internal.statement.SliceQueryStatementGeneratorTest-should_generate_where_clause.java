@Test public void should_generate_where_clause() throws Exception {
  when(sliceQuery.getFixedComponents()).thenReturn(Arrays.<Object>asList(11L,uuid1,"author"));
  RegularStatementWrapper statement=generator.generateWhereClauseForDeleteSliceQuery(sliceQuery,buildFakeDelete());
  assertThat(statement.getStatement().getQueryString()).isEqualTo("DELETE  FROM table WHERE id=11 AND a=? AND b=?;");
}
