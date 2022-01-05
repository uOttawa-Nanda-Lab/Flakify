@Test public void should_insert_only_fields_that_are_not_null() throws Exception {
  Long id=RandomUtils.nextLong();
  CompleteBean entity=new CompleteBean();
  entity.setId(id);
  entity.setName("John");
  entity.setAge(33L);
  manager2.persist(entity);
  entity.setName("Helen");
  entity.setAge(null);
  manager2.persist(entity);
  final CompleteBean found=manager2.find(CompleteBean.class,id);
  assertThat(found.getName()).isEqualTo("Helen");
  assertThat(found.getAge()).isEqualTo(33L);
}
