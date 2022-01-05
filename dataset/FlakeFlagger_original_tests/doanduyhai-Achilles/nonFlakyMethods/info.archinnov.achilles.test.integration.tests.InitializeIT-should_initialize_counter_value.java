@Test public void should_initialize_counter_value() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("name").buid();
  entity=manager.persist(entity);
  entity.getVersion().incr(2L);
  manager.update(entity);
  CompleteBean foundEntity=manager.typedQuery(CompleteBean.class,"SELECT * FROM CompleteBean WHERE id=?",entity.getId()).getFirst();
  CompleteBean rawEntity=manager.initAndRemoveProxy(foundEntity);
  assertThat(rawEntity.getVersion()).isInstanceOf(InternalCounterImpl.class);
  assertThat(rawEntity.getVersion().get()).isEqualTo(2L);
}
