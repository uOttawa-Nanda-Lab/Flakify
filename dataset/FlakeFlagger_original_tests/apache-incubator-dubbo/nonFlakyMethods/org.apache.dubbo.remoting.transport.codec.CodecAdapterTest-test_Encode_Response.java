@Test public void test_Encode_Response() throws IOException {
  ChannelBuffer encodeBuffer=ChannelBuffers.dynamicBuffer(1024);
  Channel channel=getCliendSideChannel(url);
  Response response=new Response();
  response.setHeartbeat(true);
  response.setId(1001l);
  response.setStatus((byte)20);
  response.setVersion("11");
  Person person=new Person();
  response.setResult(person);
  codec.encode(channel,encodeBuffer,response);
  byte[] data=new byte[encodeBuffer.writerIndex()];
  encodeBuffer.readBytes(data);
  ChannelBuffer decodeBuffer=ChannelBuffers.wrappedBuffer(data);
  Response obj=(Response)codec.decode(channel,decodeBuffer);
  Assert.assertEquals(response.getId(),obj.getId());
  Assert.assertEquals(response.getStatus(),obj.getStatus());
  Assert.assertEquals(response.isHeartbeat(),obj.isHeartbeat());
  Assert.assertEquals(person,obj.getResult());
}
