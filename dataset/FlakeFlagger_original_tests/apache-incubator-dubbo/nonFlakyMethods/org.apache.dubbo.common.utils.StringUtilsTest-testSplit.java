@Test public void testSplit() throws Exception {
  String s="d,1,2,4";
  assertEquals(StringUtils.split(s,',').length,4);
}
