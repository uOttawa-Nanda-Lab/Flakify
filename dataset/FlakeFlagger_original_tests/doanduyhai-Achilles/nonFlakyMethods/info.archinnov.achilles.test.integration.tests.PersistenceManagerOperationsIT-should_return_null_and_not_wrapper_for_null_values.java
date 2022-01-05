@Test public void should_return_null_and_not_wrapper_for_null_values() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("DuyHai").buid();
  entity.setFriends(null);
  entity.setFollowers(null);
  entity.setPreferences(null);
  manager.persist(entity);
  entity=manager.find(CompleteBean.class,entity.getId());
  assertThat(entity.getFriends()).isNotNull().isInstanceOf(ListWrapper.class).isEmpty();
  assertThat(entity.getFollowers()).isNull();
  assertThat(entity.getPreferences()).isNull();
  assertThat(entity.getLabel()).isNull();
  assertThat(entity.getAge()).isNull();
}
