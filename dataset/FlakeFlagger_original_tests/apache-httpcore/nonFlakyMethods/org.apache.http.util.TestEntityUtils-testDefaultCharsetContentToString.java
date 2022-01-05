@Test public void testDefaultCharsetContentToString() throws Exception {
  String content=constructString(RUSSIAN_HELLO);
  byte[] bytes=content.getBytes("KOI8-R");
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContent(new ByteArrayInputStream(bytes));
  httpentity.setContentType(new BasicHeader("Content-Type","text/plain"));
  String s=EntityUtils.toString(httpentity,"KOI8-R");
  Assert.assertEquals(content,s);
}
