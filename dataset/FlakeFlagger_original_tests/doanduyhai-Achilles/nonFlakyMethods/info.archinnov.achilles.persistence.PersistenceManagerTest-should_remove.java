@Test public void should_remove() throws Exception {
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  manager.remove(entity);
  verify(entityValidator).validateEntity(entity,entityMetaMap);
  Options options=optionsCaptor.getValue();
  assertThat(options.getConsistencyLevel().isPresent()).isFalse();
  assertThat(options.getTtl().isPresent()).isFalse();
  assertThat(options.getTimestamp().isPresent()).isFalse();
}
