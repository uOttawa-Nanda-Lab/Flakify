@Test public void should_build_dao_context() throws Exception {
  DaoContext daoContext=mock(DaoContext.class);
  when(factory.create(session,parsingResult,configContext)).thenReturn(daoContext);
  DaoContext actual=bootstrapper.buildDaoContext(session,parsingResult,configContext);
  assertThat(actual).isSameAs(daoContext);
}
