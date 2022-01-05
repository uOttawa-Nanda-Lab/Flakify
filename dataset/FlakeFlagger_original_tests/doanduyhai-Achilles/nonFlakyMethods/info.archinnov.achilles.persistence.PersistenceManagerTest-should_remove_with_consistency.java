@Test public void should_remove_with_consistency() throws Exception {
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  manager.remove(entity,OptionsBuilder.withConsistency(EACH_QUORUM));
  verify(entityValidator).validateEntity(entity,entityMetaMap);
  Options options=optionsCaptor.getValue();
  assertThat(options.getConsistencyLevel().get()).isSameAs(EACH_QUORUM);
  assertThat(options.getTtl().isPresent()).isFalse();
  assertThat(options.getTimestamp().isPresent()).isFalse();
}
