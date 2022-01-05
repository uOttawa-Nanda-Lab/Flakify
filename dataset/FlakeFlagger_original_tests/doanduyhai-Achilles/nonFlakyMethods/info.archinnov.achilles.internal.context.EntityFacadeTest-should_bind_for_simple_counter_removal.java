@Test public void should_bind_for_simple_counter_removal() throws Exception {
  PropertyMeta counterMeta=new PropertyMeta();
  facade.bindForSimpleCounterRemoval(counterMeta);
  verify(daoContext).bindForSimpleCounterDelete(context.daoFacade,counterMeta);
}
