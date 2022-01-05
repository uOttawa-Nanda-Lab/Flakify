@Test public void testChunkedTransferEncodingMustBeLast() throws Exception {
  SessionInputBuffer inbuffer=new SessionInputBufferMock("0\r\n","US-ASCII");
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);
  message.addHeader("Content-Type","unknown");
  message.addHeader("Transfer-Encoding","chunked, identity");
  message.addHeader("Content-Length","plain wrong");
  EntityDeserializer entitygen=new EntityDeserializer(new LaxContentLengthStrategy());
  HttpEntity entity=entitygen.deserialize(inbuffer,message);
  Assert.assertNotNull(entity);
  Assert.assertEquals(-1,entity.getContentLength());
  Assert.assertFalse(entity.isChunked());
  Assert.assertFalse(entity.getContent() instanceof ChunkedInputStream);
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,true);
  try {
    entitygen.deserialize(inbuffer,message);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
