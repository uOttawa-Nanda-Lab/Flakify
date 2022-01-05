@Test public void should_bind_for_simple_counter_increment() throws Exception {
  PropertyMeta counterMeta=new PropertyMeta();
  facade.bindForSimpleCounterIncrement(counterMeta,11L);
  verify(daoContext).bindForSimpleCounterIncrement(context.daoFacade,counterMeta,11L);
}
