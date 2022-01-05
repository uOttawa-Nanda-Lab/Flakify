@Test public void testEntityWithContentLength() throws Exception {
  ContentLengthStrategy lenStrategy=new StrictContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Content-Length","100");
  Assert.assertEquals(100,lenStrategy.determineLength(message));
}
