@Test public void testOneComaInExpression(){
  String testString="${first,second}";
  List<String> result=CommaSplitter.splitCommas(testString);
  assertNotNull(result);
  assertEquals(1,result.size());
  assertEquals(testString,result.get(0));
}
