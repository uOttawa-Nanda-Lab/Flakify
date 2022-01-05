@Test public void testUnknownLengthContentToString() throws Exception {
  byte[] bytes="Message content".getBytes("ISO-8859-1");
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContent(new ByteArrayInputStream(bytes));
  httpentity.setContentLength(-1L);
  String s=EntityUtils.toString(httpentity,"ISO-8859-1");
  Assert.assertEquals("Message content",s);
}
