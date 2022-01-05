@Test public void should_update_list_at_index_with_cas_condition() throws Exception {
  CompleteBean entity=builder().randomId().name("John").addFriends("Paul","Andrew").buid();
  final CompleteBean managed=manager.persist(entity);
  managed.getFriends().set(0,"Helen");
  managed.getFriends().set(1,null);
  manager.update(managed,ifConditions(new CASCondition("name","John")).withTtl(100));
  final CompleteBean actual=manager.find(CompleteBean.class,entity.getId());
  assertThat(actual.getFriends()).containsExactly("Helen");
}
