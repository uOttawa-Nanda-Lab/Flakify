@Test public void should_bind_for_clustered_counter_removal() throws Exception {
  facade.bindForClusteredCounterRemoval();
  verify(daoContext).bindForClusteredCounterDelete(context.daoFacade);
}
