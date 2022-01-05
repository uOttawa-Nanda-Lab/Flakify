@Test public void testLegacyServlet() throws Exception {
  String s=performCall("legacy","Hello");
  Assert.assertEquals("Simple Legacy Servlet called with input=Hello",s);
}
