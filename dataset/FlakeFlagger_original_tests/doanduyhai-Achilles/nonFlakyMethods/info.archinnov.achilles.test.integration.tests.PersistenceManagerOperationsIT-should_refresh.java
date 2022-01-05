@Test public void should_refresh() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").buid();
  entity=manager.persist(entity);
  entity.getFriends();
  session.execute("UPDATE completebean SET name='DuyHai_modified' WHERE id=" + entity.getId());
  session.execute("UPDATE completebean SET friends=friends + ['qux'] WHERE id=" + entity.getId());
  manager.refresh(entity);
  assertThat(entity.getName()).isEqualTo("DuyHai_modified");
  assertThat(entity.getFriends()).hasSize(3);
  assertThat(entity.getFriends().get(2)).isEqualTo("qux");
}
