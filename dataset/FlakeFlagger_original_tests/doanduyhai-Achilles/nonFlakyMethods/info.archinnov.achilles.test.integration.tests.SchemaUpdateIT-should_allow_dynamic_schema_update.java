@Test public void should_allow_dynamic_schema_update() throws Exception {
  Long id=RandomUtils.nextLong();
  final Session session=CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("schema_dynamic_update").cleanDataFilesAtStartup(true).buildNativeSessionOnly();
  session.execute("CREATE TABLE new_simple_field(id bigint PRIMARY KEY, existing_field text)");
  final int cqlPort=CASSANDRA_EMBEDDED.getCQLPort();
  Cluster cluster=Cluster.builder().addContactPoint("localhost").withPort(cqlPort).build();
  final PersistenceManagerFactory pmf=PersistenceManagerFactoryBuilder.builder(cluster).withEntities(Arrays.<Class<?>>asList(EntityWithNewSimpleField.class)).withKeyspaceName("schema_dynamic_update").enableSchemaUpdate(false).enableSchemaUpdateForTables(ImmutableMap.of("new_simple_field",true)).build();
  final PersistenceManager pm=pmf.createPersistenceManager();
  pm.persist(new EntityWithNewSimpleField(id,"existing","new"));
  assertThat(pm.find(EntityWithNewSimpleField.class,id)).isNotNull();
}
