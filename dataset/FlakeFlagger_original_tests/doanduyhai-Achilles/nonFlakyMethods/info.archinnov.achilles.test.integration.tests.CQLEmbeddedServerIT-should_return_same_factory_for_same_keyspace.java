@Test public void should_return_same_factory_for_same_keyspace() throws Exception {
  PersistenceManagerFactory factory1=CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("third_keyspace").buildPersistenceManagerFactory();
  PersistenceManagerFactory factory2=CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("third_keyspace").buildPersistenceManagerFactory();
  assertThat(factory1).isSameAs(factory2);
}
