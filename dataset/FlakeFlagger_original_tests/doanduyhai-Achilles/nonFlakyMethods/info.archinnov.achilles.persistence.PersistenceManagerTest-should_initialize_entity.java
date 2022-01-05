@Test public void should_initialize_entity() throws Exception {
  when(facade.initialize(entity)).thenReturn(entity);
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  CompleteBean actual=manager.initialize(entity);
  verify(proxifier).ensureProxy(entity);
  assertThat(actual).isSameAs(entity);
  Options options=optionsCaptor.getValue();
  assertThat(options.getConsistencyLevel().isPresent()).isFalse();
  assertThat(options.getTtl().isPresent()).isFalse();
  assertThat(options.getTimestamp().isPresent()).isFalse();
}
