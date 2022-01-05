@Test public void should_apply_pre_remove_interceptors() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").label("label").buid();
  manager.remove(entity);
  assertThat(entity.getName()).isEqualTo("preRemove");
}
