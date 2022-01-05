@Test public void testEntityWithInvalidChunkEncodingAndHTTP10() throws Exception {
  SessionOutputBuffer outbuffer=new SessionOutputBufferMock();
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_0);
  message.addHeader("Transfer-Encoding","chunked");
  EntitySerializer entitywriter=new EntitySerializer(new StrictContentLengthStrategy());
  try {
    entitywriter.doSerialize(outbuffer,message);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
