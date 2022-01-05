@Test public void should_get_simple_counter() throws Exception {
  PropertyMeta counterMeta=new PropertyMeta();
  Row row=mock(Row.class);
  when(daoContext.getSimpleCounter(context.daoFacade,counterMeta,LOCAL_QUORUM)).thenReturn(row);
  when(row.getLong(CQL_COUNTER_VALUE)).thenReturn(11L);
  Long counterValue=facade.getSimpleCounter(counterMeta,LOCAL_QUORUM);
  assertThat(counterValue).isEqualTo(11L);
}
