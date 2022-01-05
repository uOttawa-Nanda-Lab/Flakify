@Test public void testExtractNullContentType() throws Exception {
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContentType((Header)null);
  Assert.assertNull(ContentType.get(httpentity));
}
