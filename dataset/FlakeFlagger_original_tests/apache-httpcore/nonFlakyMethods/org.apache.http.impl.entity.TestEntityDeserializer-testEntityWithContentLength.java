@Test public void testEntityWithContentLength() throws Exception {
  SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{});
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);
  message.addHeader("Content-Type","unknown");
  message.addHeader("Content-Length","0");
  EntityDeserializer entitygen=new EntityDeserializer(new LaxContentLengthStrategy());
  HttpEntity entity=entitygen.deserialize(inbuffer,message);
  Assert.assertNotNull(entity);
  Assert.assertEquals(0,entity.getContentLength());
  Assert.assertFalse(entity.isChunked());
  Assert.assertTrue(entity.getContent() instanceof ContentLengthInputStream);
}
