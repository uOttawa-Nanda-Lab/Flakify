@Test public void should_override_consistency_by_map() throws Exception {
  final PersistenceManager pm=CassandraEmbeddedServerBuilder.withEntities(Arrays.<Class<?>>asList(EntityWithTwoConsistency.class)).withKeyspaceName("consistency_map_override").cleanDataFilesAtStartup(true).withAchillesConfigParams(ImmutableMap.<ConfigurationParameters,Object>of(CONSISTENCY_LEVEL_READ_MAP,ImmutableMap.of(EntityWithTwoConsistency.TABLE_NAME,QUORUM),CONSISTENCY_LEVEL_WRITE_MAP,ImmutableMap.of(EntityWithTwoConsistency.TABLE_NAME,ONE))).buildPersistenceManager();
  logAsserter.prepareLogLevel();
  final EntityWithTwoConsistency entity=new EntityWithTwoConsistency();
  entity.setId(RandomUtils.nextLong());
  pm.persist(entity);
  assertThat(pm.find(EntityWithTwoConsistency.class,entity.getId())).isNotNull();
  logAsserter.assertConsistencyLevels(ONE,QUORUM);
}
