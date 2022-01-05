@Test public void testSerialize() throws Exception {
  UriInfo uriInfo=createMock(UriInfo.class);
  Resource resource=createMock(Resource.class);
  Result result=new ResultImpl(true);
  TreeNode<Resource> tree=result.getResultTree();
  TreeNode<Resource> child=tree.addChild(resource,"resource1");
  HashMap<String,Object> mapRootProps=new HashMap<String,Object>();
  mapRootProps.put("prop1","value1");
  mapRootProps.put("prop2","value2");
  HashMap<String,Object> mapCategoryProps=new HashMap<String,Object>();
  mapCategoryProps.put("catProp1","catValue1");
  mapCategoryProps.put("catProp2","catValue2");
  TreeNode<Map<String,Object>> treeProps1=new TreeNodeImpl<Map<String,Object>>(null,mapRootProps,null);
  treeProps1.addChild(mapCategoryProps,"category");
  expect(resource.getProperties()).andReturn(treeProps1).anyTimes();
  expect(resource.getType()).andReturn(Resource.Type.Cluster).anyTimes();
  replay(uriInfo,resource);
  Object o=new JsonSerializer().serialize(result,uriInfo);
  String expected="{\n" + "  \"prop2\" : \"value2\",\n" + "  \"prop1\" : \"value1\",\n"+ "  \"category\" : {\n"+ "    \"catProp1\" : \"catValue1\",\n"+ "    \"catProp2\" : \"catValue2\"\n"+ "  }\n"+ "}";
  assertEquals(expected,o);
  verify(uriInfo,resource);
}
