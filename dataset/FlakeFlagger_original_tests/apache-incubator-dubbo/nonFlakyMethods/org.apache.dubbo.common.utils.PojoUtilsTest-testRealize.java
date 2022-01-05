@Test public void testRealize() throws Exception {
  Map<String,String> map=new LinkedHashMap<String,String>();
  map.put("key","value");
  Object obj=PojoUtils.generalize(map);
  assertTrue(obj instanceof LinkedHashMap);
  Object outputObject=PojoUtils.realize(map,LinkedHashMap.class);
  assertTrue(outputObject instanceof LinkedHashMap);
  Object[] objects=PojoUtils.realize(new Object[]{map},new Class[]{LinkedHashMap.class});
  assertTrue(objects[0] instanceof LinkedHashMap);
  assertEquals(objects[0],outputObject);
}
