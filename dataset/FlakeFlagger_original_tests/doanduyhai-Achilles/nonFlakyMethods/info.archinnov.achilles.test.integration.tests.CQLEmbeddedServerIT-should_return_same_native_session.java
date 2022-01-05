@Test public void should_return_same_native_session() throws Exception {
  Session session=CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("test_keyspace").buildNativeSessionOnly();
  assertThat(session).isSameAs(this.session);
}
