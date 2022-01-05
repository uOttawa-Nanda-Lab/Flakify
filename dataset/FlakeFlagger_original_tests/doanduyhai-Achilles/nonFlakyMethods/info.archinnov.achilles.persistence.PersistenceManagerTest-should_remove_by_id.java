@Test public void should_remove_by_id() throws Exception {
  when(contextFactory.newContext(CompleteBean.class,primaryKey,OptionsBuilder.noOptions())).thenReturn(context);
  when(facade.getIdMeta()).thenReturn(idMeta);
  manager.removeById(CompleteBean.class,primaryKey);
  verify(entityValidator).validatePrimaryKey(idMeta,primaryKey);
  verify(facade).remove();
}
