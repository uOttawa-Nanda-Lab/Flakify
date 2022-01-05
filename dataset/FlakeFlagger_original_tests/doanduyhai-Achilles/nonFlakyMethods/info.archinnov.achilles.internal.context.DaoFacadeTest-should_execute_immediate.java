@Test public void should_execute_immediate() throws Exception {
  BoundStatementWrapper bsWrapper=mock(BoundStatementWrapper.class);
  ResultSet resultSet=mock(ResultSet.class);
  when(flushContext.executeImmediate(bsWrapper)).thenReturn(resultSet);
  ResultSet actual=facade.executeImmediate(bsWrapper);
  assertThat(actual).isSameAs(resultSet);
}
