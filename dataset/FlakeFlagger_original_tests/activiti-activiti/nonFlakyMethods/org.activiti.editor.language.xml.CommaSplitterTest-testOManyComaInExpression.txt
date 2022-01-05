@Test public void testOManyComaInExpression(){
  String testString="${Everything,should,be,made,as,simple,as,possible},but,no,simpler";
  List<String> result=CommaSplitter.splitCommas(testString);
  assertNotNull(result);
  assertEquals(4,result.size());
  assertEquals("${Everything,should,be,made,as,simple,as,possible}",result.get(0));
  assertEquals("but",result.get(1));
  assertEquals("no",result.get(2));
  assertEquals("simpler",result.get(3));
}
