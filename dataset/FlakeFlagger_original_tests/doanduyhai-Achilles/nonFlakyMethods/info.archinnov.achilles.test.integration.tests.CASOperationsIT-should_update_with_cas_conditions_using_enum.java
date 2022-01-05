@Test public void should_update_with_cas_conditions_using_enum() throws Exception {
  final EntityWithEnum entityWithEnum=new EntityWithEnum(10L,"John",EACH_QUORUM);
  final EntityWithEnum managed=manager.persist(entityWithEnum);
  managed.setName("Helen");
  manager.update(managed,ifConditions(new CASCondition("name","John"),new CASCondition("consistency_level",EACH_QUORUM)));
  final EntityWithEnum found=manager.find(EntityWithEnum.class,10L);
  assertThat(found).isNotNull();
  assertThat(found.getName()).isEqualTo("Helen");
  assertThat(found.getConsistencyLevel()).isEqualTo(EACH_QUORUM);
}
