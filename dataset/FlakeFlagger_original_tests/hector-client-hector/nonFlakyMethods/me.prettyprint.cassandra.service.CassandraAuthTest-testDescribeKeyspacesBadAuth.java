@Test public void testDescribeKeyspacesBadAuth() throws Exception {
  ThriftCluster cassandraCluster=new ThriftCluster("Test Cluster",cassandraHostConfigurator,user1CredentialsBad);
  try {
    cassandraCluster.describeKeyspaces();
    fail("Should have gotten a wrapped AuthenticationException");
  }
 catch (  HectorException he) {
    assertTrue(he.getCause() instanceof AuthenticationException);
  }
}
