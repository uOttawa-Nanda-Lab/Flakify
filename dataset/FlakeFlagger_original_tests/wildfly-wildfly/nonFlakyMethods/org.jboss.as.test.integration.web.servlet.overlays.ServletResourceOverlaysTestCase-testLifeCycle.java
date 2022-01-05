@Test public void testLifeCycle() throws Exception {
  String result=performCall(url,"a.txt");
  assertEquals("a",result);
  result=performCall(url,"b.txt");
  assertEquals("b",result);
  result=performCall(url,"c.txt");
  assertEquals("c - overlay",result);
}
