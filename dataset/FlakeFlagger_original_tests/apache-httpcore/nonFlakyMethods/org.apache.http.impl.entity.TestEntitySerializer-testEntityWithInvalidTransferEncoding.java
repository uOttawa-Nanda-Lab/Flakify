@Test public void testEntityWithInvalidTransferEncoding() throws Exception {
  SessionOutputBuffer outbuffer=new SessionOutputBufferMock();
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Transfer-Encoding","whatever");
  EntitySerializer entitywriter=new EntitySerializer(new StrictContentLengthStrategy());
  try {
    entitywriter.doSerialize(outbuffer,message);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
