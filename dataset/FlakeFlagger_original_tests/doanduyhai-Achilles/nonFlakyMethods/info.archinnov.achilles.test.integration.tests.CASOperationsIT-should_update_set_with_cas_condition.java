@Test public void should_update_set_with_cas_condition() throws Exception {
  CompleteBean entity=builder().randomId().name("John").addFollowers("Paul","Andrew").buid();
  final CompleteBean managed=manager.persist(entity);
  managed.getFollowers().add("Helen");
  managed.getFollowers().remove("Paul");
  manager.update(managed,ifConditions(new CASCondition("name","John")).withTtl(100));
  final CompleteBean actual=manager.find(CompleteBean.class,entity.getId());
  assertThat(actual.getFollowers()).containsOnly("Helen","Andrew");
}
