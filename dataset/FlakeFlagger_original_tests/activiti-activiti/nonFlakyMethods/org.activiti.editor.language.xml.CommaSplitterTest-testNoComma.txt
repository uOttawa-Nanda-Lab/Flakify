@Test public void testNoComma(){
  String testString="Test String";
  List<String> result=CommaSplitter.splitCommas(testString);
  assertNotNull(result);
  assertEquals(1,result.size());
  assertEquals(testString,result.get(0));
}
