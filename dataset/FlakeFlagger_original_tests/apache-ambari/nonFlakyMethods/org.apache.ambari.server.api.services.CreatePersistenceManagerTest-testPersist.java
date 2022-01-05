@Test public void testPersist() throws Exception {
  ResourceInstance resource=createMock(ResourceInstance.class);
  ResourceDefinition resourceDefinition=createMock(ResourceDefinition.class);
  ClusterController controller=createMock(ClusterController.class);
  Schema schema=createMock(Schema.class);
  String clusterId="clusterId";
  String serviceId="serviceId";
  Request serverRequest=createStrictMock(Request.class);
  Map<Resource.Type,String> mapResourceIds=new HashMap<Resource.Type,String>();
  mapResourceIds.put(Resource.Type.Cluster,"clusterId");
  mapResourceIds.put(Resource.Type.Service,"serviceId");
  Set<Map<String,Object>> setProperties=new HashSet<Map<String,Object>>();
  Map<String,Object> mapProperties=new HashMap<String,Object>();
  mapProperties.put(clusterId,"clusterId");
  mapProperties.put(serviceId,"serviceId");
  mapProperties.put(PropertyHelper.getPropertyId("foo","bar"),"value");
  setProperties.add(mapProperties);
  expect(resource.getIds()).andReturn(mapResourceIds);
  expect(resource.getResourceDefinition()).andReturn(resourceDefinition).atLeastOnce();
  expect(resourceDefinition.getType()).andReturn(Resource.Type.Component);
  expect(controller.getSchema(Resource.Type.Component)).andReturn(schema);
  expect(schema.getKeyPropertyId(Resource.Type.Cluster)).andReturn(clusterId);
  expect(schema.getKeyPropertyId(Resource.Type.Service)).andReturn(serviceId);
  expect(controller.createResources(Resource.Type.Component,serverRequest)).andReturn(new RequestStatusImpl(null));
  replay(resource,resourceDefinition,controller,schema,serverRequest);
  new TestCreatePersistenceManager(controller,setProperties,serverRequest).persist(resource,setProperties);
  verify(resource,resourceDefinition,controller,schema,serverRequest);
}
