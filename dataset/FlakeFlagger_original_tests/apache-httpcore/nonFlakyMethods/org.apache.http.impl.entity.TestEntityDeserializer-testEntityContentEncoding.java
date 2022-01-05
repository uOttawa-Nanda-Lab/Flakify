@Test public void testEntityContentEncoding() throws Exception {
  SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{'0'});
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Content-Encoding","what not");
  EntityDeserializer entitygen=new EntityDeserializer(new LaxContentLengthStrategy());
  HttpEntity entity=entitygen.deserialize(inbuffer,message);
  Assert.assertNotNull(entity);
  Assert.assertNotNull(entity.getContentEncoding());
  Assert.assertEquals("what not",entity.getContentEncoding().getValue());
}
