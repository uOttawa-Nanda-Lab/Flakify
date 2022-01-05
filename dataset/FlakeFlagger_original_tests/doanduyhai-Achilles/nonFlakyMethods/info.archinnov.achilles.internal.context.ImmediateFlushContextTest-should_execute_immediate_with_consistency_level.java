@Test public void should_execute_immediate_with_consistency_level() throws Exception {
  ResultSet result=mock(ResultSet.class);
  when(daoContext.execute(bsWrapper)).thenReturn(result);
  ResultSet actual=context.executeImmediate(bsWrapper);
  assertThat(actual).isSameAs(result);
}
