@Test public void should_prepare_statement() throws Exception {
  RegularStatement statement=new SimpleStatement("query");
  when(session.prepare("query")).thenReturn(ps);
  assertThat(daoContext.prepare(statement)).isSameAs(ps);
}
