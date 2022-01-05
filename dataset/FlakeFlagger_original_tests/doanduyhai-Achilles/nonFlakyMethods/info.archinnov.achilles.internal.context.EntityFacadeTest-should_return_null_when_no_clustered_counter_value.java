@Test public void should_return_null_when_no_clustered_counter_value() throws Exception {
  when(daoContext.getClusteredCounter(context.daoFacade)).thenReturn(null);
  assertThat(facade.getClusteredCounter()).isNull();
}
