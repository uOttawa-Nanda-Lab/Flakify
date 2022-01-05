@Test public void testRenderEntryAndMakeSureMapIsCreated(){
  String stringy=new String("stringy");
  Result result=new Result(200);
  result.render("stringy",stringy);
  Map<String,Object> resultMap=(Map)result.getRenderable();
  assertEquals(stringy,resultMap.get("stringy"));
}
