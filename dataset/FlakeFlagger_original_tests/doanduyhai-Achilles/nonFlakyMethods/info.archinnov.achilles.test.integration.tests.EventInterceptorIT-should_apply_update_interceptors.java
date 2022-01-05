@Test public void should_apply_update_interceptors() throws Exception {
  CompleteBean entity=builder().randomId().buid();
  entity=manager.persist(entity);
  entity.setName("DuyHai");
  entity.setLabel("label");
  manager.update(entity);
  Row row=session.execute("select name,label from CompleteBean where id = " + entity.getId()).one();
  assertThat(row.getString("name")).isEqualTo("preUpdate");
  assertThat(row.getString("label")).isEqualTo("label");
  assertThat(entity.getName()).isEqualTo("preUpdate");
  assertThat(entity.getLabel()).isEqualTo("postUpdate");
}
