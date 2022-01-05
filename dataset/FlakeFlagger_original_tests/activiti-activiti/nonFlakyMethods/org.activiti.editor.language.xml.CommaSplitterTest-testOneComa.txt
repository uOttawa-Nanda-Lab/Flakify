@Test public void testOneComa(){
  String testString="Test,String";
  List<String> result=CommaSplitter.splitCommas(testString);
  assertNotNull(result);
  assertEquals(2,result.size());
  assertEquals("Test",result.get(0));
  assertEquals("String",result.get(1));
}
