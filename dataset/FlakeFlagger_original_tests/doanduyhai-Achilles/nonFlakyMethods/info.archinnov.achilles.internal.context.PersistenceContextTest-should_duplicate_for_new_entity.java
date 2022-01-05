@Test public void should_duplicate_for_new_entity() throws Exception {
  CompleteBean entity=new CompleteBean();
  entity.setId(primaryKey);
  when(meta.getPrimaryKey(entity)).thenReturn(primaryKey);
  when(flushContext.duplicate()).thenReturn(flushContext);
  PersistenceContext duplicateContext=context.duplicate(entity);
  assertThat(duplicateContext.stateHolderFacade.getEntity()).isSameAs(entity);
  assertThat(duplicateContext.stateHolderFacade.getPrimaryKey()).isSameAs(primaryKey);
}
