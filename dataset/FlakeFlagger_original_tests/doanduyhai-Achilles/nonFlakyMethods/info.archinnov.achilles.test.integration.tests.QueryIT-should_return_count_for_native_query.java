@Test public void should_return_count_for_native_query() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").buid();
  manager.persist(entity);
  Long count=(Long)manager.nativeQuery("SELECT COUNT(*) FROM CompleteBean WHERE id=" + entity.getId()).first().get("count");
  assertThat(count).isEqualTo(1L);
}
