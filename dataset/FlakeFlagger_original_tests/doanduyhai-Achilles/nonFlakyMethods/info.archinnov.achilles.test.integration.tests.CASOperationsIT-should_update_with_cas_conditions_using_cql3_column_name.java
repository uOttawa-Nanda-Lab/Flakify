@Test public void should_update_with_cas_conditions_using_cql3_column_name() throws Exception {
  final Long primaryKey=RandomUtils.nextLong();
  final CompleteBean entity=new CompleteBean();
  entity.setId(primaryKey);
  entity.setAge(32L);
  entity.setName("John");
  final CompleteBean managed=manager.persist(entity);
  managed.setName("Helen");
  manager.update(managed,ifConditions(new CASCondition("age_in_years",32L)));
  final CompleteBean found=manager.find(CompleteBean.class,primaryKey);
  assertThat(found).isNotNull();
  assertThat(found.getName()).isEqualTo("Helen");
}
