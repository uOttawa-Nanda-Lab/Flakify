@Test public void testServlet() throws Exception {
  String s=performCall("mapping/foo","Hello");
  Assert.assertEquals("foo:/mapping/*:PathMappingServlet",s);
}
