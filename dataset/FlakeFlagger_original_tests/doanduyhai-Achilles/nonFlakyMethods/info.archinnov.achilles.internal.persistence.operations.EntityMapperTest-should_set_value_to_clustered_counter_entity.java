@Test public void should_set_value_to_clustered_counter_entity() throws Exception {
  Long counterValue=10L;
  PropertyMeta counterMeta=mock(PropertyMeta.class);
  when(counterMeta.getPropertyName()).thenReturn("counter");
  when(entityMeta.getAllCounterMetas()).thenReturn(asList(counterMeta));
  when(cqlRowInvoker.invokeOnRowForType(row,Long.class,"counter")).thenReturn(counterValue);
  entityMapper.setValuesToClusteredCounterEntity(row,entityMeta,entity);
  verify(counterMeta).setValueToField(eq(entity),counterCaptor.capture());
  assertThat(counterCaptor.getValue().get()).isEqualTo(counterValue);
}
