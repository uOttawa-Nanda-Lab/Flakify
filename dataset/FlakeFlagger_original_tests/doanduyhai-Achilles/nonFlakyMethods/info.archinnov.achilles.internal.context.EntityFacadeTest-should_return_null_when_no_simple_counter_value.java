@Test public void should_return_null_when_no_simple_counter_value() throws Exception {
  PropertyMeta counterMeta=new PropertyMeta();
  when(daoContext.getSimpleCounter(context.daoFacade,counterMeta,LOCAL_QUORUM)).thenReturn(null);
  assertThat(facade.getSimpleCounter(counterMeta,LOCAL_QUORUM)).isNull();
}
