@Test public void should_bootstrap_achilles_without_entity_package_for_native_query() throws Exception {
  Map<String,Object> keyspaceMap=manager.nativeQuery("SELECT keyspace_name from system.schema_keyspaces where keyspace_name='system'").first();
  assertThat(keyspaceMap).hasSize(1);
  assertThat(keyspaceMap.get("keyspace_name")).isEqualTo("system");
}
