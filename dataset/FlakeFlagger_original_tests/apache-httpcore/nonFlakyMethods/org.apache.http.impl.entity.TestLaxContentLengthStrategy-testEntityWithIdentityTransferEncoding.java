@Test public void testEntityWithIdentityTransferEncoding() throws Exception {
  ContentLengthStrategy lenStrategy=new LaxContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);
  message.addHeader("Content-Type","unknown");
  message.addHeader("Transfer-Encoding","identity");
  message.addHeader("Content-Length","plain wrong");
  Assert.assertEquals(ContentLengthStrategy.IDENTITY,lenStrategy.determineLength(message));
}
