@Test public void testAddProperty__subProperty(){
  ResourceInstance resource=createNiceMock(ResourceInstance.class);
  ResourceDefinition resourceDefinition=createNiceMock(ResourceDefinition.class);
  ResourceInstance subResource=createNiceMock(ResourceInstance.class);
  Schema schema=createNiceMock(Schema.class);
  Map<String,Set<String>> mapSchemaProps=new HashMap<String,Set<String>>();
  mapSchemaProps.put("category",Collections.singleton("property"));
  mapSchemaProps.put(null,Collections.singleton("property2"));
  expect(resource.getResourceDefinition()).andReturn(resourceDefinition).anyTimes();
  expect(resourceDefinition.getType()).andReturn(Resource.Type.Service).anyTimes();
  expect(m_controller.getSchema(Resource.Type.Service)).andReturn(schema).anyTimes();
  expect(schema.getCategoryProperties()).andReturn(mapSchemaProps).anyTimes();
  expect(resource.getSubResources()).andReturn(Collections.singletonMap("components",subResource)).anyTimes();
  replay(m_controller,resource,resourceDefinition,subResource,schema);
  Query query=new TestQuery(resource,null);
  query.addProperty(null,"components",null);
  verify(m_controller,resource,resourceDefinition,subResource,schema);
}
