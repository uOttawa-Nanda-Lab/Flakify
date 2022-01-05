@Test public void testLifeCycle() throws Exception {
  String result=performCall(url,"LifeCycleMethodServlet");
  assertEquals("ok",result);
}
