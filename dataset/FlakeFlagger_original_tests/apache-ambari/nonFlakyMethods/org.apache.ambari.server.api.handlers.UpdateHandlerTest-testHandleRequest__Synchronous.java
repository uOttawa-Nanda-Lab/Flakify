@Test public void testHandleRequest__Synchronous(){
  Request request=createMock(Request.class);
  ResourceInstance resource=createMock(ResourceInstance.class);
  PersistenceManager pm=createStrictMock(PersistenceManager.class);
  RequestStatus status=createMock(RequestStatus.class);
  Resource resource1=createMock(Resource.class);
  Resource resource2=createMock(Resource.class);
  Predicate userPredicate=createNiceMock(Predicate.class);
  Query query=createNiceMock(Query.class);
  Set<Map<String,Object>> setResourceProperties=new HashSet<Map<String,Object>>();
  Set<Resource> setResources=new HashSet<Resource>();
  setResources.add(resource1);
  setResources.add(resource2);
  expect(request.getPersistenceManager()).andReturn(pm).atLeastOnce();
  expect(request.getResource()).andReturn(resource).atLeastOnce();
  expect(request.getHttpBodyProperties()).andReturn(setResourceProperties).atLeastOnce();
  expect(request.getURI()).andReturn("http://some.host.com:8080/clusters/cluster1/hosts/host1").atLeastOnce();
  expect(request.getQueryPredicate()).andReturn(userPredicate).atLeastOnce();
  expect(resource.getQuery()).andReturn(query).atLeastOnce();
  query.setUserPredicate(userPredicate);
  expect(pm.persist(resource,setResourceProperties)).andReturn(status);
  expect(status.getStatus()).andReturn(RequestStatus.Status.Complete);
  expect(status.getAssociatedResources()).andReturn(setResources);
  expect(resource1.getType()).andReturn(Resource.Type.Cluster).anyTimes();
  expect(resource2.getType()).andReturn(Resource.Type.Cluster).anyTimes();
  replay(request,resource,pm,status,resource1,resource2,userPredicate,query);
  Result result=new UpdateHandler().handleRequest(request);
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
  verify(request,resource,pm,status,resource1,resource2,userPredicate,query);
}
