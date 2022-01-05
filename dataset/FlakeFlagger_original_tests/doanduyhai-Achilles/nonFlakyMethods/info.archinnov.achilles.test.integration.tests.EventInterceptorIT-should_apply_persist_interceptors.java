@Test public void should_apply_persist_interceptors() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").label("label").buid();
  manager.persist(entity);
  Row row=session.execute("select name,label from CompleteBean where id = " + entity.getId()).one();
  assertThat(row.getString("name")).isEqualTo("prePersist");
  assertThat(row.getString("label")).isEqualTo("label");
  assertThat(entity.getName()).isEqualTo("prePersist");
  assertThat(entity.getLabel()).isEqualTo("postPersist");
}
