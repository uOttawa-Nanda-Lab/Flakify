@Test public void testToString() throws Exception {
  StatusLine statusline=new BasicStatusLine(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  Assert.assertEquals("HTTP/1.1 200 OK",statusline.toString());
  statusline=new BasicStatusLine(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,null);
  Assert.assertEquals("HTTP/1.1 200 ",statusline.toString());
}
