@Test(expected=AchillesInvalidTableException.class) public void should_fail_bootstrapping_if_schema_update_forbidden_for_entity() throws Exception {
  final Session session=CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("schema_dynamic_update_fail").cleanDataFilesAtStartup(true).buildNativeSessionOnly();
  session.execute("CREATE TABLE new_simple_field(id bigint PRIMARY KEY, existing_field text)");
  final int cqlPort=CASSANDRA_EMBEDDED.getCQLPort();
  Cluster cluster=Cluster.builder().addContactPoint("localhost").withPort(cqlPort).build();
  final PersistenceManagerFactory pmf=PersistenceManagerFactoryBuilder.builder(cluster).withEntities(Arrays.<Class<?>>asList(EntityWithNewSimpleField.class)).withKeyspaceName("schema_dynamic_update_fail").enableSchemaUpdate(true).enableSchemaUpdateForTables(ImmutableMap.of("new_simple_field",false)).build();
}
