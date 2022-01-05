@Test public void should_remove_transient_entity() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").buid();
  manager.persist(entity);
  manager.remove(entity);
  assertThat(manager.find(CompleteBean.class,entity.getId())).isNull();
}
