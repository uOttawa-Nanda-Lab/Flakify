@Test public void should_apply_post_remove_interceptors() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").label("label").buid();
  manager2.remove(entity);
  assertThat(entity.getLabel()).isEqualTo("postRemove");
}
