@Test public void testManyCommas(){
  String testString="does,anybody,realy,reads,this,nonsense";
  List<String> result=CommaSplitter.splitCommas(testString);
  assertNotNull(result);
  assertEquals(6,result.size());
  assertEquals("does",result.get(0));
  assertEquals("anybody",result.get(1));
  assertEquals("realy",result.get(2));
  assertEquals("reads",result.get(3));
  assertEquals("this",result.get(4));
  assertEquals("nonsense",result.get(5));
}
