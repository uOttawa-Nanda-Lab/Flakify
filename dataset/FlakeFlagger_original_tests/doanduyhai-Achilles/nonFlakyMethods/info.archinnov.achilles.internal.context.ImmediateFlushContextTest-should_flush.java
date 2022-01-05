@Test public void should_flush() throws Exception {
  List<AbstractStatementWrapper> statementWrappers=new ArrayList<AbstractStatementWrapper>();
  statementWrappers.add(bsWrapper);
  Whitebox.setInternalState(context,"statementWrappers",statementWrappers);
  context.flush();
  verify(daoContext).execute(bsWrapper);
}
