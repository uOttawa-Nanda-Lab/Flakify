@Test(expected=ProtocolException.class) public void testEntityWithInvalidContentLength() throws Exception {
  ContentLengthStrategy lenStrategy=new StrictContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Content-Length","whatever");
  lenStrategy.determineLength(message);
}
