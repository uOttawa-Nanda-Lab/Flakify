@Test public void should_bind_for_removal() throws Exception {
  facade.bindForRemoval("table");
  verify(daoContext).bindForRemoval(context.daoFacade,"table");
}
