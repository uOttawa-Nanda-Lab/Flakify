@Test public void testEntityWithUnsupportedTransferEncoding() throws Exception {
  ContentLengthStrategy lenStrategy=new LaxContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);
  message.addHeader("Content-Type","unknown");
  message.addHeader("Transfer-Encoding","whatever; param=value, chunked");
  message.addHeader("Content-Length","plain wrong");
  Assert.assertEquals(ContentLengthStrategy.CHUNKED,lenStrategy.determineLength(message));
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,true);
  try {
    lenStrategy.determineLength(message);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
