@Test public void testDescribeKeyspace() throws Exception {
  KeyspaceDefinition keyspaceDetail=cassandraCluster.describeKeyspace("Keyspace1");
  assertNotNull(keyspaceDetail);
  assertEquals(7,keyspaceDetail.getCfDefs().size());
}
