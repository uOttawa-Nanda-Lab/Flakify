@Test public void testCloning() throws Exception {
  BasicStatusLine orig=new BasicStatusLine(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  BasicStatusLine clone=(BasicStatusLine)orig.clone();
  Assert.assertEquals(orig.getReasonPhrase(),clone.getReasonPhrase());
  Assert.assertEquals(orig.getStatusCode(),clone.getStatusCode());
  Assert.assertEquals(orig.getProtocolVersion(),clone.getProtocolVersion());
}
