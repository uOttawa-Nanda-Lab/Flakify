@Test public void should_return_same_manager_for_same_keyspace() throws Exception {
  PersistenceManager manager1=CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("second_keyspace").buildPersistenceManager();
  PersistenceManager manager2=CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("second_keyspace").buildPersistenceManager();
  assertThat(manager1).isSameAs(manager2);
}
