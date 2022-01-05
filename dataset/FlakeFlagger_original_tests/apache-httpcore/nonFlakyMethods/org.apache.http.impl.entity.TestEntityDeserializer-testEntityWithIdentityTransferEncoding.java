@Test public void testEntityWithIdentityTransferEncoding() throws Exception {
  SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{});
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);
  message.addHeader("Content-Type","unknown");
  message.addHeader("Transfer-Encoding","identity");
  message.addHeader("Content-Length","plain wrong");
  EntityDeserializer entitygen=new EntityDeserializer(new LaxContentLengthStrategy());
  HttpEntity entity=entitygen.deserialize(inbuffer,message);
  Assert.assertNotNull(entity);
  Assert.assertEquals(-1,entity.getContentLength());
  Assert.assertFalse(entity.isChunked());
}
