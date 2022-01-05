@Test public void testServlet() throws Exception {
  String s=performCall("simple","Hello");
  Assert.assertEquals("Simple Servlet called with input=Hello",s);
}
