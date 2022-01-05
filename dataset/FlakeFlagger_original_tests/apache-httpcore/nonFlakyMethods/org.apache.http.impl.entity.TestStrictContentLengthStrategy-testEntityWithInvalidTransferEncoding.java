@Test(expected=ProtocolException.class) public void testEntityWithInvalidTransferEncoding() throws Exception {
  ContentLengthStrategy lenStrategy=new StrictContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Transfer-Encoding","whatever");
  lenStrategy.determineLength(message);
}
