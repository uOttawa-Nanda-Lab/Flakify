@Test public void testDescribeKeyspaces() throws Exception {
  List<KeyspaceDefinition> keyspaces=cassandraCluster.describeKeyspaces();
  assertEquals(2,keyspaces.size());
}
