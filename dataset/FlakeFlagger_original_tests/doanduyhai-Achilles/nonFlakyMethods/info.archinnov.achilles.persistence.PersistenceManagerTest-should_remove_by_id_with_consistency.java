@Test public void should_remove_by_id_with_consistency() throws Exception {
  when(contextFactory.newContext(eq(CompleteBean.class),eq(primaryKey),optionsCaptor.capture())).thenReturn(context);
  when(facade.getIdMeta()).thenReturn(idMeta);
  manager.removeById(CompleteBean.class,primaryKey,LOCAL_QUORUM);
  verify(entityValidator).validatePrimaryKey(idMeta,primaryKey);
  verify(facade).remove();
  Options options=optionsCaptor.getValue();
  assertThat(options.getConsistencyLevel().get()).isSameAs(LOCAL_QUORUM);
  assertThat(options.getTtl().isPresent()).isFalse();
  assertThat(options.getTimestamp().isPresent()).isFalse();
}
