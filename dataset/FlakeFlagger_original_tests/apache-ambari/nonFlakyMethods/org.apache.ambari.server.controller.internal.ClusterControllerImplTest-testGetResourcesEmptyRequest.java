@Test public void testGetResourcesEmptyRequest() throws Exception {
  ClusterController controller=new ClusterControllerImpl(new TestProviderModule());
  Set<String> propertyIds=new HashSet<String>();
  Request request=PropertyHelper.getReadRequest(propertyIds);
  Iterable<Resource> iterable=controller.getResources(Resource.Type.Host,request,null);
  int cnt=0;
  for (  Resource resource : iterable) {
    Assert.assertEquals(Resource.Type.Host,resource.getType());
    ++cnt;
  }
  Assert.assertEquals(4,cnt);
}
