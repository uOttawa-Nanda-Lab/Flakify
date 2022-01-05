@Test public void should_bootstrap_embedded_server_and_entity_manager() throws Exception {
  Long id=RandomUtils.nextLong();
  manager.persist(new User(id,"fn","ln"));
  Row row=session.execute("SELECT * FROM User WHERE id=" + id).one();
  assertThat(row).isNotNull();
  assertThat(row.getString("firstname")).isEqualTo("fn");
  assertThat(row.getString("lastname")).isEqualTo("ln");
}
