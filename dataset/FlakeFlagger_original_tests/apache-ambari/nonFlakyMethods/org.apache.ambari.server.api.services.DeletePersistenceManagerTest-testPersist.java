@Test public void testPersist() throws Exception {
  ResourceInstance resource=createNiceMock(ResourceInstance.class);
  ResourceDefinition resourceDefinition=createNiceMock(ResourceDefinition.class);
  ClusterController controller=createMock(ClusterController.class);
  Query query=createMock(Query.class);
  Predicate predicate=createMock(Predicate.class);
  expect(resource.getResourceDefinition()).andReturn(resourceDefinition).anyTimes();
  expect(resourceDefinition.getType()).andReturn(Resource.Type.Component).anyTimes();
  expect(resource.getQuery()).andReturn(query).anyTimes();
  expect(query.getPredicate()).andReturn(predicate).anyTimes();
  expect(controller.deleteResources(Resource.Type.Component,predicate)).andReturn(new RequestStatusImpl(null));
  replay(resource,resourceDefinition,controller,query,predicate);
  new TestDeletePersistenceManager(controller).persist(resource,null);
  verify(resource,resourceDefinition,controller,query,predicate);
}
