@Test public void should_apply_post_load_interceptor_on_typed_query() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").label("label").buid();
  manager.persist(entity);
  final CompleteBean actual=manager.typedQuery(CompleteBean.class,"SELECT * FROM CompleteBean WHERE id=?",entity.getId()).getFirst();
  assertThat(actual.getLabel()).isEqualTo("postLoad");
}
