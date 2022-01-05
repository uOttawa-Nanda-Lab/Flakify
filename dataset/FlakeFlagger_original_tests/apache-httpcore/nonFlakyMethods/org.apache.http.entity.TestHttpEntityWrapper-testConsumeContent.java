@Test public void testConsumeContent() throws Exception {
  String s="Message content";
  StringEntity httpentity=new StringEntity(s);
  HttpEntityWrapper wrapped=new HttpEntityWrapper(httpentity);
  EntityUtils.consume(wrapped);
  EntityUtils.consume(wrapped);
}
