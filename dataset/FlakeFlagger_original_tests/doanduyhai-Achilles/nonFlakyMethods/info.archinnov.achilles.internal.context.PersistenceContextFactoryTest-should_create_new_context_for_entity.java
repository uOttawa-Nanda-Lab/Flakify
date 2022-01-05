@Test public void should_create_new_context_for_entity() throws Exception {
  Long primaryKey=RandomUtils.nextLong();
  CompleteBean entity=new CompleteBean(primaryKey);
  when(proxifier.<CompleteBean>deriveBaseClass(entity)).thenReturn(CompleteBean.class);
  when(meta.getPrimaryKey(entity)).thenReturn(primaryKey);
  PersistenceContext actual=pmf.newContext(entity);
  assertThat(actual.stateHolderFacade.getEntity()).isSameAs(entity);
  assertThat(actual.stateHolderFacade.getPrimaryKey()).isSameAs(primaryKey);
  assertThat(actual.stateHolderFacade.<CompleteBean>getEntityClass()).isSameAs(CompleteBean.class);
  assertThat(actual.stateHolderFacade.getEntityMeta()).isSameAs(meta);
  assertThat(actual.stateHolderFacade.getIdMeta()).isSameAs(idMeta);
  assertThat(actual.stateHolderFacade.getTtl()).isSameAs(PersistenceContextFactory.NO_TTL);
}
