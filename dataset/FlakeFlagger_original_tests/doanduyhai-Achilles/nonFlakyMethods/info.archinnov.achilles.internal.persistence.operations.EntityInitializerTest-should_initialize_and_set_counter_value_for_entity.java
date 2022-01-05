@Test public void should_initialize_and_set_counter_value_for_entity() throws Exception {
  when(meta.getAllCounterMetas()).thenReturn(Arrays.asList(counterMeta));
  initializer.initializeEntity(bean,meta);
  verify(counterMeta).invokeGetter(bean);
}
