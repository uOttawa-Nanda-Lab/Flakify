@Test public void should_execute_query() throws Exception {
  ResultSet resultSet=mock(ResultSet.class);
  when(bsWrapper.execute(session)).thenReturn(resultSet);
  ResultSet actual=daoContext.execute(bsWrapper);
  assertThat(actual).isSameAs(resultSet);
}
