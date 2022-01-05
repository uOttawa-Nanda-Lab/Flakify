@Test public void testGetInvalidCluster() throws AmbariException {
  try {
    clusters.getCluster("foo");
    fail("Exception should be thrown on invalid get");
  }
 catch (  ClusterNotFoundException e) {
  }
}
