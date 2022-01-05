@Test public void testPersist() throws Exception {
  ResourceInstance resource=createMock(ResourceInstance.class);
  ResourceDefinition resourceDefinition=createMock(ResourceDefinition.class);
  ClusterController controller=createMock(ClusterController.class);
  Schema schema=createMock(Schema.class);
  Request serverRequest=createStrictMock(Request.class);
  Query query=createMock(Query.class);
  Predicate predicate=createMock(Predicate.class);
  Set<Map<String,Object>> setProperties=new HashSet<Map<String,Object>>();
  Map<String,Object> mapProperties=new HashMap<String,Object>();
  mapProperties.put(PropertyHelper.getPropertyId("foo","bar"),"value");
  setProperties.add(mapProperties);
  expect(resource.getResourceDefinition()).andReturn(resourceDefinition);
  expect(resourceDefinition.getType()).andReturn(Resource.Type.Component);
  expect(resource.getQuery()).andReturn(query);
  expect(query.getPredicate()).andReturn(predicate);
  expect(controller.updateResources(Resource.Type.Component,serverRequest,predicate)).andReturn(new RequestStatusImpl(null));
  replay(resource,resourceDefinition,controller,schema,serverRequest,query,predicate);
  new TestUpdatePersistenceManager(controller,setProperties,serverRequest).persist(resource,setProperties);
  verify(resource,resourceDefinition,controller,schema,serverRequest,query,predicate);
}
