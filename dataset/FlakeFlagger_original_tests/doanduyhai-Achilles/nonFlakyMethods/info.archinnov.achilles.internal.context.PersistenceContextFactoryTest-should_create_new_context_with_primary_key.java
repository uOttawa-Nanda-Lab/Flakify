@Test public void should_create_new_context_with_primary_key() throws Exception {
  Object primaryKey=RandomUtils.nextLong();
  PersistenceContext context=pmf.newContext(CompleteBean.class,primaryKey,OptionsBuilder.withConsistency(LOCAL_QUORUM).withTtl(98));
  assertThat(context.stateHolderFacade.getEntity()).isNull();
  assertThat(context.stateHolderFacade.getPrimaryKey()).isSameAs(primaryKey);
  assertThat(context.stateHolderFacade.<CompleteBean>getEntityClass()).isSameAs(CompleteBean.class);
  assertThat(context.stateHolderFacade.getEntityMeta()).isSameAs(meta);
  assertThat(context.stateHolderFacade.getIdMeta()).isSameAs(idMeta);
  assertThat(context.stateHolderFacade.getTtl().get()).isEqualTo(98);
}
