@Test public void testDescribeKeyspaces() throws Exception {
  ThriftCluster cassandraCluster=new ThriftCluster("Test Cluster",cassandraHostConfigurator,user1Credentials);
  List<KeyspaceDefinition> keyspaces=cassandraCluster.describeKeyspaces();
  assertEquals(2,keyspaces.size());
}
