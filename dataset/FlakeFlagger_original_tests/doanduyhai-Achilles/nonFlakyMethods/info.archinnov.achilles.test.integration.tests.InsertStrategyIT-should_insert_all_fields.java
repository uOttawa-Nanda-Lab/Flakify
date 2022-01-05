@Test public void should_insert_all_fields() throws Exception {
  Long id=RandomUtils.nextLong();
  CompleteBean entity=new CompleteBean();
  entity.setId(id);
  entity.setName("John");
  entity.setAge(33L);
  manager1.persist(entity);
  entity.setName("Helen");
  entity.setAge(null);
  manager1.persist(entity);
  final CompleteBean found=manager1.find(CompleteBean.class,id);
  assertThat(found.getName()).isEqualTo("Helen");
  assertThat(found.getAge()).isNull();
}
