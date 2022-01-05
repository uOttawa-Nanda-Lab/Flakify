@Test public void testSLFormatting() throws Exception {
  StatusLine statusline=new BasicStatusLine(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  String s=BasicLineFormatter.formatStatusLine(statusline,null);
  Assert.assertEquals("HTTP/1.1 200 OK",s);
  statusline=new BasicStatusLine(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,null);
  s=BasicLineFormatter.formatStatusLine(statusline,null);
  Assert.assertEquals("HTTP/1.1 200 ",s);
}
