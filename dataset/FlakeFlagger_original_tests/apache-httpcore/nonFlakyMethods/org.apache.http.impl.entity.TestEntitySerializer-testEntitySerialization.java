@Test public void testEntitySerialization() throws Exception {
  byte[] content=new byte[]{1,2,3,4,5};
  ByteArrayEntity entity=new ByteArrayEntity(content);
  SessionOutputBufferMock outbuffer=new SessionOutputBufferMock();
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Content-Length",Integer.toString(content.length));
  EntitySerializer entitywriter=new EntitySerializer(new StrictContentLengthStrategy());
  entitywriter.serialize(outbuffer,message,entity);
  byte[] data=outbuffer.getData();
  Assert.assertNotNull(data);
  Assert.assertEquals(content.length,data.length);
}
