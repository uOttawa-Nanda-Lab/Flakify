@Test public void should_overwrite_existing_values_on_persist() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("DuyHai").addFriends("foo","bar","qux").addFollowers("John","Helen").addPreference(1,"Paris").addPreference(2,"Ile de France").addPreference(3,"FRANCE").buid();
  manager.persist(entity);
  entity.getFriends().clear();
  entity.getFollowers().clear();
  entity.getPreferences().clear();
  manager.persist(entity);
  entity=manager.find(CompleteBean.class,entity.getId());
  assertThat(entity.getFriends()).isNotNull().isEmpty();
  assertThat(entity.getFollowers()).isNull();
  assertThat(entity.getPreferences()).isNull();
}
