@Test public void testHandleRequest__Synchronous(){
  Request request=createNiceMock(Request.class);
  ResourceInstance resource=createNiceMock(ResourceInstance.class);
  PersistenceManager pm=createStrictMock(PersistenceManager.class);
  RequestStatus status=createNiceMock(RequestStatus.class);
  Resource resource1=createNiceMock(Resource.class);
  Resource resource2=createNiceMock(Resource.class);
  Set<Map<String,Object>> setResourceProperties=new HashSet<Map<String,Object>>();
  Set<Resource> setResources=new HashSet<Resource>();
  setResources.add(resource1);
  setResources.add(resource2);
  expect(request.getPersistenceManager()).andReturn(pm).atLeastOnce();
  expect(request.getResource()).andReturn(resource).atLeastOnce();
  expect(request.getQueryPredicate()).andReturn(null).atLeastOnce();
  expect(request.getHttpBodyProperties()).andReturn(setResourceProperties).atLeastOnce();
  expect(request.getURI()).andReturn("http://some.host.com:8080/clusters/cluster1/services/HDFS").atLeastOnce();
  expect(pm.persist(resource,setResourceProperties)).andReturn(status);
  expect(status.getStatus()).andReturn(RequestStatus.Status.Complete);
  expect(status.getAssociatedResources()).andReturn(setResources);
  expect(resource1.getType()).andReturn(Resource.Type.Cluster).anyTimes();
  expect(resource2.getType()).andReturn(Resource.Type.Cluster).anyTimes();
  replay(request,resource,pm,status,resource1,resource2);
  Result result=new CreateHandler().handleRequest(request);
  assertNotNull(result);
  TreeNode<Resource> tree=result.getResultTree();
  assertEquals(1,tree.getChildren().size());
  TreeNode<Resource> resourcesNode=tree.getChild("resources");
  assertEquals(2,resourcesNode.getChildren().size());
  boolean foundResource1=false;
  boolean foundResource2=false;
  for (  TreeNode<Resource> child : resourcesNode.getChildren()) {
    Resource r=child.getObject();
    if (r == resource1 && !foundResource1) {
      foundResource1=true;
    }
 else     if (r == resource2 && !foundResource2) {
      foundResource2=true;
    }
 else {
      fail();
    }
  }
  verify(request,resource,pm,status,resource1,resource2);
}
