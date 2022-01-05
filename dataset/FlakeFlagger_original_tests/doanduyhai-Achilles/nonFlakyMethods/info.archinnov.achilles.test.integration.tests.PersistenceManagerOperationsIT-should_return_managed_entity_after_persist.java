@Test public void should_return_managed_entity_after_persist() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().buid();
  entity=manager.persist(entity);
  assertThat(entity).isInstanceOf(Factory.class);
}
