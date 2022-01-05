@Test public void testHandleRequest__Asynchronous(){
  Request request=createNiceMock(Request.class);
  ResourceInstance resource=createNiceMock(ResourceInstance.class);
  PersistenceManager pm=createStrictMock(PersistenceManager.class);
  RequestStatus status=createNiceMock(RequestStatus.class);
  Resource resource1=createNiceMock(Resource.class);
  Resource resource2=createNiceMock(Resource.class);
  Resource requestResource=createNiceMock(Resource.class);
  Set<Map<String,Object>> setResourceProperties=new HashSet<Map<String,Object>>();
  Set<Resource> setResources=new HashSet<Resource>();
  setResources.add(resource1);
  setResources.add(resource2);
  expect(request.getResource()).andReturn(resource);
  expect(request.getHttpBodyProperties()).andReturn(setResourceProperties);
  expect(request.getPersistenceManager()).andReturn(pm);
  expect(request.getQueryPredicate()).andReturn(null).atLeastOnce();
  expect(pm.persist(resource,setResourceProperties)).andReturn(status);
  expect(status.getStatus()).andReturn(RequestStatus.Status.Accepted);
  expect(status.getAssociatedResources()).andReturn(setResources);
  expect(resource1.getType()).andReturn(Resource.Type.Cluster).anyTimes();
  expect(resource2.getType()).andReturn(Resource.Type.Cluster).anyTimes();
  expect(status.getRequestResource()).andReturn(requestResource);
  expect(request.getURI()).andReturn("http://some.host.com:8080/clusters/cluster1/services/HDFS").atLeastOnce();
  expect(status.getRequestResource()).andReturn(requestResource).atLeastOnce();
  expect(requestResource.getPropertyValue(PropertyHelper.getPropertyId("Requests","id"))).andReturn("requestID").atLeastOnce();
  replay(request,resource,pm,status,resource1,resource2,requestResource);
  Result result=new CreateHandler().handleRequest(request);
  assertNotNull(result);
  TreeNode<Resource> tree=result.getResultTree();
  assertEquals(2,tree.getChildren().size());
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
  TreeNode<Resource> statusNode=tree.getChild("request");
  assertNotNull(statusNode);
  assertEquals(0,statusNode.getChildren().size());
  assertSame(requestResource,statusNode.getObject());
  assertEquals("http://some.host.com:8080/clusters/cluster1/requests/requestID",statusNode.getProperty("href"));
  verify(request,resource,pm,status,resource1,resource2,requestResource);
}
