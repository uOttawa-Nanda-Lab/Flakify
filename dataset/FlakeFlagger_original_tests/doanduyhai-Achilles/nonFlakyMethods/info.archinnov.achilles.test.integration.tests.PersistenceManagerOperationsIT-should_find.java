@Test public void should_find() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("Jonathan").buid();
  manager.persist(entity);
  CompleteBean found=manager.find(CompleteBean.class,entity.getId());
  assertThat(found).isNotNull();
  assertThat(found.getName()).isEqualTo("Jonathan");
}
