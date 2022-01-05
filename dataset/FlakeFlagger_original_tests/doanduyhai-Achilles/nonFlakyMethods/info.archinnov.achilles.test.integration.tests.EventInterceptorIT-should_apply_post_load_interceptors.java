@Test public void should_apply_post_load_interceptors() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").label("label").buid();
  manager.persist(entity);
  entity=manager.find(CompleteBean.class,entity.getId());
  assertThat(entity.getLabel()).isEqualTo("postLoad");
}
