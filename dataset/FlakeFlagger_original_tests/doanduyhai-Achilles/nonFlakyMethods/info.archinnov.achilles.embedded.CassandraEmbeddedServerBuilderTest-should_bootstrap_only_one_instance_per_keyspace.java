@Test public void should_bootstrap_only_one_instance_per_keyspace() throws Exception {
  PersistenceManagerFactory factory1=CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("keyspace1").buildPersistenceManagerFactory();
  PersistenceManagerFactory factory2=CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("keyspace2").buildPersistenceManagerFactory();
  PersistenceManagerFactory factory3=CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("keyspace1").buildPersistenceManagerFactory();
  assertThat(factory1).isNotEqualTo(factory2);
  assertThat(factory1).isEqualTo(factory3);
}
