@Test public void should_create_resource_with_a_distinct_keyspace() throws Exception {
  AchillesResource resource=AchillesResourceBuilder.noEntityPackages("test_keyspace");
  final PersistenceManager manager=resource.getPersistenceManager();
  final Map<String,Object> map=manager.nativeQuery("SELECT count(1) FROM system.schema_keyspaces WHERE keyspace_name='test_keyspace'").first();
  assertThat(map.get("count")).isEqualTo(1L);
}
