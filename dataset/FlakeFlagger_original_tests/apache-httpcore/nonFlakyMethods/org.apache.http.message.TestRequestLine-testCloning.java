@Test public void testCloning() throws Exception {
  BasicRequestLine orig=new BasicRequestLine("GET","/stuff",HttpVersion.HTTP_1_1);
  BasicRequestLine clone=(BasicRequestLine)orig.clone();
  Assert.assertEquals(orig.getMethod(),clone.getMethod());
  Assert.assertEquals(orig.getUri(),clone.getUri());
  Assert.assertEquals(orig.getProtocolVersion(),clone.getProtocolVersion());
}
