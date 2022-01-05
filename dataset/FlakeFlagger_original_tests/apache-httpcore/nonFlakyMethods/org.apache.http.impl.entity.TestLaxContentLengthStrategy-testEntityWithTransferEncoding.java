@Test public void testEntityWithTransferEncoding() throws Exception {
  ContentLengthStrategy lenStrategy=new LaxContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);
  message.addHeader("Content-Type","unknown");
  message.addHeader("Transfer-Encoding","identity, chunked");
  message.addHeader("Content-Length","plain wrong");
  Assert.assertEquals(ContentLengthStrategy.CHUNKED,lenStrategy.determineLength(message));
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,true);
  Assert.assertEquals(ContentLengthStrategy.CHUNKED,lenStrategy.determineLength(message));
}
