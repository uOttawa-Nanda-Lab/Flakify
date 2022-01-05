@Test public void testContentWithContentTypeToString() throws Exception {
  String content=constructString(RUSSIAN_HELLO);
  byte[] bytes=content.getBytes("UTF-8");
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContent(new ByteArrayInputStream(bytes));
  httpentity.setContentType(new BasicHeader("Content-Type","text/plain; charset=UTF-8"));
  String s=EntityUtils.toString(httpentity,"ISO-8859-1");
  Assert.assertEquals(content,s);
}
