@Test public void testEntityWithMultipleContentLength() throws Exception {
  ContentLengthStrategy lenStrategy=new LaxContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);
  message.addHeader("Content-Type","unknown");
  message.addHeader("Content-Length","0");
  message.addHeader("Content-Length","0");
  message.addHeader("Content-Length","1");
  Assert.assertEquals(1,lenStrategy.determineLength(message));
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,true);
  try {
    lenStrategy.determineLength(message);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
