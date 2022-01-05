@Test public void testAddProperty__localCategorySubPropsOnly(){
  ResourceInstance resource=createNiceMock(ResourceInstance.class);
  ResourceDefinition resourceDefinition=createNiceMock(ResourceDefinition.class);
  Schema schema=createNiceMock(Schema.class);
  Map<String,Set<String>> mapSchemaProps=new HashMap<String,Set<String>>();
  Set<String> setInnerProps=new HashSet<String>();
  setInnerProps.add("property3");
  setInnerProps.add("property4");
  mapSchemaProps.put("category/nestedCategory",setInnerProps);
  mapSchemaProps.put(null,Collections.singleton("property5"));
  expect(resource.getResourceDefinition()).andReturn(resourceDefinition).anyTimes();
  expect(resourceDefinition.getType()).andReturn(Resource.Type.Service).anyTimes();
  expect(m_controller.getSchema(Resource.Type.Service)).andReturn(schema).anyTimes();
  expect(schema.getCategoryProperties()).andReturn(mapSchemaProps).anyTimes();
  replay(m_controller,resource,resourceDefinition,schema);
  Query query=new TestQuery(resource,null);
  query.addProperty(null,"category",null);
  Map<String,Set<String>> mapProperties=query.getProperties();
  assertEquals(2,mapProperties.size());
  assertTrue(mapProperties.get("category").isEmpty());
  Set<String> setResultProps=mapProperties.get("category/nestedCategory");
  assertEquals(2,setResultProps.size());
  assertTrue(setResultProps.contains("property3"));
  assertTrue(setResultProps.contains("property4"));
  verify(m_controller,resource,resourceDefinition,schema);
}
