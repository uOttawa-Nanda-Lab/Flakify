@Test public void should_persist_empty_entity() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().buid();
  manager.persist(entity);
  CompleteBean found=manager.find(CompleteBean.class,entity.getId());
  assertThat(found).isNotNull();
  assertThat(found.getId()).isEqualTo(entity.getId());
  assertThat(found.getFriends()).isNotNull().isEmpty();
  assertThat(found.getFollowers()).isNull();
  assertThat(found.getPreferences()).isNull();
}
