@Test public void testAddProperty__localProperty(){
  ResourceInstance resource=createNiceMock(ResourceInstance.class);
  ResourceDefinition resourceDefinition=createNiceMock(ResourceDefinition.class);
  Schema schema=createNiceMock(Schema.class);
  Map<String,Set<String>> mapSchemaProps=new HashMap<String,Set<String>>();
  mapSchemaProps.put("category",Collections.singleton("property"));
  mapSchemaProps.put(null,Collections.singleton("property2"));
  expect(resource.getResourceDefinition()).andReturn(resourceDefinition).anyTimes();
  expect(resourceDefinition.getType()).andReturn(Resource.Type.Service).anyTimes();
  expect(m_controller.getSchema(Resource.Type.Service)).andReturn(schema);
  expect(schema.getCategoryProperties()).andReturn(mapSchemaProps);
  replay(m_controller,resource,resourceDefinition,schema);
  Query query=new TestQuery(resource,null);
  query.addProperty("category","property",null);
  assertEquals(1,query.getProperties().size());
  assertEquals(Collections.singleton("property"),query.getProperties().get("category"));
  query.addProperty(null,"property2",null);
  assertEquals(2,query.getProperties().size());
  assertEquals(Collections.singleton("property2"),query.getProperties().get(null));
  verify(m_controller,resource,resourceDefinition,schema);
}
