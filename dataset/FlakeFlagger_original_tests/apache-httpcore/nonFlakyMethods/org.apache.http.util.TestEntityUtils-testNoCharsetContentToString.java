@Test public void testNoCharsetContentToString() throws Exception {
  String content=constructString(SWISS_GERMAN_HELLO);
  byte[] bytes=content.getBytes("ISO-8859-1");
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContent(new ByteArrayInputStream(bytes));
  httpentity.setContentType(new BasicHeader("Content-Type","text/plain"));
  String s=EntityUtils.toString(httpentity);
  Assert.assertEquals(content,s);
}
