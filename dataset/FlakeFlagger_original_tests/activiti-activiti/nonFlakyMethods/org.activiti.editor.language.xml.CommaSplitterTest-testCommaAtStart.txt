@Test public void testCommaAtStart(){
  String testString=",first,second";
  List<String> result=CommaSplitter.splitCommas(testString);
  assertNotNull(result);
  assertEquals(2,result.size());
  assertEquals("first",result.get(0));
  assertEquals("second",result.get(1));
}
