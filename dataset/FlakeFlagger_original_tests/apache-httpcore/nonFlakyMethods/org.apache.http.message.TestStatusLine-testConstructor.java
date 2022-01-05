@Test public void testConstructor(){
  StatusLine statusline=new BasicStatusLine(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  Assert.assertEquals(HttpVersion.HTTP_1_1,statusline.getProtocolVersion());
  Assert.assertEquals(HttpStatus.SC_OK,statusline.getStatusCode());
  Assert.assertEquals("OK",statusline.getReasonPhrase());
}
