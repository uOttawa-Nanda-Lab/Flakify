@Test public void testConvertToResponse() throws AmbariException {
  ClusterResponse r=c1.convertToResponse();
  Assert.assertEquals(c1.getClusterId(),r.getClusterId().longValue());
  Assert.assertEquals(c1.getClusterName(),r.getClusterName());
  Assert.assertEquals(1,r.getHostNames().size());
  StringBuilder sb=new StringBuilder();
  c1.debugDump(sb);
}
