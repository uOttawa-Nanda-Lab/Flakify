@Test public void should_get_reference_with_consistency() throws Exception {
  when(contextFactory.newContext(eq(CompleteBean.class),eq(primaryKey),optionsCaptor.capture())).thenReturn(context);
  when(facade.getProxy(CompleteBean.class)).thenReturn(entity);
  when(facade.getIdMeta()).thenReturn(idMeta);
  when(entityMetaMap.containsKey(CompleteBean.class)).thenReturn(true);
  CompleteBean bean=manager.getProxy(CompleteBean.class,primaryKey,EACH_QUORUM);
  verify(entityValidator).validatePrimaryKey(idMeta,primaryKey);
  assertThat(bean).isSameAs(entity);
  Options options=optionsCaptor.getValue();
  assertThat(options.getConsistencyLevel().get()).isSameAs(EACH_QUORUM);
  assertThat(options.getTtl().isPresent()).isFalse();
  assertThat(options.getTimestamp().isPresent()).isFalse();
}
