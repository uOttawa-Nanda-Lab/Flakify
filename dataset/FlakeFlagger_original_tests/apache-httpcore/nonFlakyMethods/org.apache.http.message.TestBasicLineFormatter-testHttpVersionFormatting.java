@Test public void testHttpVersionFormatting() throws Exception {
  String s=BasicLineFormatter.formatProtocolVersion(HttpVersion.HTTP_1_1,null);
  Assert.assertEquals("HTTP/1.1",s);
}
