@Test public void should_find() throws Exception {
  when(contextFactory.newContext(eq(CompleteBean.class),eq(primaryKey),optionsCaptor.capture())).thenReturn(context);
  when(facade.find(CompleteBean.class)).thenReturn(entity);
  PropertyMeta idMeta=new PropertyMeta();
  when(facade.getIdMeta()).thenReturn(idMeta);
  when(entityMetaMap.containsKey(CompleteBean.class)).thenReturn(true);
  CompleteBean bean=manager.find(CompleteBean.class,primaryKey);
  verify(entityValidator).validatePrimaryKey(idMeta,primaryKey);
  assertThat(bean).isSameAs(entity);
  Options options=optionsCaptor.getValue();
  assertThat(options.getConsistencyLevel().isPresent()).isFalse();
  assertThat(options.getTtl().isPresent()).isFalse();
  assertThat(options.getTimestamp().isPresent()).isFalse();
}
