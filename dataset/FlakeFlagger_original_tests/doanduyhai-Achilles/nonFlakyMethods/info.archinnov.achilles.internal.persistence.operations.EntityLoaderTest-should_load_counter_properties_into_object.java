@Test public void should_load_counter_properties_into_object() throws Exception {
  when(pm.type()).thenReturn(PropertyType.COUNTER);
  loader.loadPropertyIntoObject(context,entity,pm);
  verify(counterLoader).loadCounter(context,entity,pm);
  verifyZeroInteractions(mapper);
}
