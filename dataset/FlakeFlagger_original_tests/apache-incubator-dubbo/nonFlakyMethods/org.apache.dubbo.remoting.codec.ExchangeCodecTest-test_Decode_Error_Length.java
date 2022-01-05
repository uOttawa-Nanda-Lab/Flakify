@Test public void test_Decode_Error_Length() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,0x02,20,0,0,0,0,0,0,0,0,0,0,0,0};
  Person person=new Person();
  byte[] request=getRequestBytes(person,header);
  Channel channel=getServerSideChannel(url);
  byte[] baddata=new byte[]{1,2};
  ChannelBuffer buffer=ChannelBuffers.wrappedBuffer(join(request,baddata));
  Response obj=(Response)codec.decode(channel,buffer);
  Assert.assertEquals(person,obj.getResult());
  Assert.assertEquals(request.length,buffer.readerIndex());
}
