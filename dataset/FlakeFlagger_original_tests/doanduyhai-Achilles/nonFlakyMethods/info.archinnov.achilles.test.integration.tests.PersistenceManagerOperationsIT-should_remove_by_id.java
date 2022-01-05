@Test public void should_remove_by_id() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").buid();
  entity=manager.persist(entity);
  manager.removeById(CompleteBean.class,entity.getId());
  assertThat(manager.find(CompleteBean.class,entity.getId())).isNull();
  List<Row> rows=session.execute("select * from completebean where id=" + entity.getId()).all();
  assertThat(rows).isEmpty();
}
