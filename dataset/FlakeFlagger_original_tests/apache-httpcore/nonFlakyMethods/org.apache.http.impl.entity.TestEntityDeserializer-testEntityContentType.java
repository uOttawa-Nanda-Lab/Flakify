@Test public void testEntityContentType() throws Exception {
  SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{'0'});
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Content-Type","stuff");
  EntityDeserializer entitygen=new EntityDeserializer(new LaxContentLengthStrategy());
  HttpEntity entity=entitygen.deserialize(inbuffer,message);
  Assert.assertNotNull(entity);
  Assert.assertNotNull(entity.getContentType());
  Assert.assertEquals("stuff",entity.getContentType().getValue());
}
