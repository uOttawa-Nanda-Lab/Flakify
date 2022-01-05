@Test public void should_push_clustered_counter_increment() throws Exception {
  PropertyMeta counterMeta=new PropertyMeta();
  facade.pushClusteredCounterIncrementStatement(counterMeta,11L);
  verify(daoContext).pushClusteredCounterIncrementStatement(context.daoFacade,counterMeta,11L);
}
