@Test(expected=ProtocolException.class) public void testEntityWithInvalidChunkEncodingAndHTTP10() throws Exception {
  ContentLengthStrategy lenStrategy=new StrictContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_0);
  message.addHeader("Transfer-Encoding","chunked");
  lenStrategy.determineLength(message);
}
