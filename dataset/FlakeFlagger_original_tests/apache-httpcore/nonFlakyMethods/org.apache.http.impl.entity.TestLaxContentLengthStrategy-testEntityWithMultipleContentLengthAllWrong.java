@Test public void testEntityWithMultipleContentLengthAllWrong() throws Exception {
  ContentLengthStrategy lenStrategy=new LaxContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);
  message.addHeader("Content-Type","unknown");
  message.addHeader("Content-Length","yyy");
  message.addHeader("Content-Length","xxx");
  Assert.assertEquals(ContentLengthStrategy.IDENTITY,lenStrategy.determineLength(message));
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,true);
  try {
    lenStrategy.determineLength(message);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
