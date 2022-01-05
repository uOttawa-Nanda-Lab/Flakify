@Test public void should_allow_dynamic_schema_update_for_cluster_counter() throws Exception {
  Long id=RandomUtils.nextLong();
  UUID date=UUIDGen.getTimeUUID();
  final Session session=CassandraEmbeddedServerBuilder.withEntities(Arrays.<Class<?>>asList(CompleteBean.class)).withKeyspaceName("schema_dynamic_update_counter").cleanDataFilesAtStartup(true).buildNativeSessionOnly();
  session.execute("CREATE TABLE new_counter_field(id bigint, date timeuuid, existing_counter counter, PRIMARY KEY(id,date))");
  final int cqlPort=CASSANDRA_EMBEDDED.getCQLPort();
  Cluster cluster=Cluster.builder().addContactPoint("localhost").withPort(cqlPort).build();
  final PersistenceManagerFactory pmf=PersistenceManagerFactoryBuilder.builder(cluster).withEntities(Arrays.<Class<?>>asList(ClusteredCounterEntityWithNewCounterField.class)).withKeyspaceName("schema_dynamic_update_counter").enableSchemaUpdate(true).build();
  final PersistenceManager pm=pmf.createPersistenceManager();
  pm.persist(new ClusteredCounterEntityWithNewCounterField(id,date,CounterBuilder.incr(12L)));
  assertThat(pm.find(ClusteredCounterEntityWithNewCounterField.class,new ClusteredCounterEntityWithNewCounterField.Compound(id,date))).isNotNull();
}
