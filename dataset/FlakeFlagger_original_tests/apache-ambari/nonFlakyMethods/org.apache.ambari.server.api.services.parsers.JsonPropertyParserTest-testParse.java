@Test public void testParse() throws Exception {
  RequestBodyParser parser=new JsonPropertyParser();
  Set<Map<String,Object>> setProps=parser.parse(serviceJson);
  assertEquals(1,setProps.size());
  Map<String,Object> mapExpected=new HashMap<String,Object>();
  mapExpected.put(PropertyHelper.getPropertyId("Services","service_name"),"HDFS");
  mapExpected.put(PropertyHelper.getPropertyId("Services","display_name"),"HDFS");
  mapExpected.put(PropertyHelper.getPropertyId("ServiceInfo","cluster_name"),"tbmetrictest");
  mapExpected.put(PropertyHelper.getPropertyId("Services","attributes"),"{ \"runnable\": true, \"mustInstall\": true, \"editable\": false, \"noDisplay\": false }");
  mapExpected.put(PropertyHelper.getPropertyId("Services","description"),"Apache Hadoop Distributed File System");
  mapExpected.put(PropertyHelper.getPropertyId("ServiceInfo","state"),"STARTED");
  mapExpected.put(PropertyHelper.getPropertyId("OuterCategory","propName"),"100");
  mapExpected.put(PropertyHelper.getPropertyId("OuterCategory.nested1.nested2","innerPropName"),"innerPropValue");
  assertEquals(mapExpected,setProps.iterator().next());
}
