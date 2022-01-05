@Test public void should_get_clustered_counter() throws Exception {
  PropertyMeta counterMeta=new PropertyMeta();
  counterMeta.setPropertyName("count");
  Long counterValue=11L;
  when(daoContext.getClusteredCounterColumn(context.daoFacade,counterMeta)).thenReturn(counterValue);
  Long actual=facade.getClusteredCounterColumn(counterMeta);
  assertThat(actual).isEqualTo(counterValue);
}
