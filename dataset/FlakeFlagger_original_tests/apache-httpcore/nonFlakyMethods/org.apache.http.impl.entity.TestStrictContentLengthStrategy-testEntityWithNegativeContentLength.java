@Test(expected=ProtocolException.class) public void testEntityWithNegativeContentLength() throws Exception {
  ContentLengthStrategy lenStrategy=new StrictContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Content-Length","-10");
  lenStrategy.determineLength(message);
}
