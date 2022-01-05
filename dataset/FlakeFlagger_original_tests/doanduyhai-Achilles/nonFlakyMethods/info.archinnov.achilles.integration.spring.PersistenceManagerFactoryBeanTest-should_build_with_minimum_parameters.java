@Test public void should_build_with_minimum_parameters() throws Exception {
  when(cluster.connect("keyspace")).thenThrow(IllegalStateException.class);
  factory.setEntityPackages("info.archinnov.achilles.test.integration.entity");
  factory.setKeyspaceName("keyspace");
  factory.setCluster(cluster);
  exception.expect(IllegalStateException.class);
  factory.initialize();
}
