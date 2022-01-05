@Test public void testRenderMultipleObjects(){
  TestObject testObject=new TestObject();
  Result result=new Result(200);
  result.render(testObject);
  assertEquals(testObject,result.getRenderable());
  String string=new String("test");
  result.render(string);
  assertTrue(result.getRenderable() instanceof Map);
  Map<String,Object> resultMap=(Map)result.getRenderable();
  assertEquals(string,resultMap.get("string"));
  assertEquals(testObject,resultMap.get("testObject"));
  TestObject anotherObject=new TestObject();
  boolean gotException=false;
  try {
    result.render(anotherObject);
  }
 catch (  IllegalArgumentException e) {
    gotException=true;
  }
  assertTrue(gotException);
  Entry<String,Object> entry=new AbstractMap.SimpleImmutableEntry<String,Object>("anotherObject",anotherObject);
  result.render(entry);
  resultMap=(Map)result.getRenderable();
  assertEquals(3,resultMap.size());
  assertEquals(anotherObject,resultMap.get("anotherObject"));
  Map<String,Object> mapToRender=Maps.newHashMap();
  String anotherString=new String("anotherString");
  TestObject anotherTestObject=new TestObject();
  mapToRender.put("anotherString",anotherString);
  mapToRender.put("anotherTestObject",anotherTestObject);
  result.render(mapToRender);
  resultMap=(Map)result.getRenderable();
  assertEquals(2,resultMap.size());
  assertEquals(anotherString,resultMap.get("anotherString"));
  assertEquals(anotherTestObject,resultMap.get("anotherTestObject"));
}
