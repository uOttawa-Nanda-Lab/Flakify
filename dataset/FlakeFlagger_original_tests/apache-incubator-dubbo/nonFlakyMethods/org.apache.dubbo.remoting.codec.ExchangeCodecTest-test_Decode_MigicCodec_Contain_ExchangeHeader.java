@Test public void test_Decode_MigicCodec_Contain_ExchangeHeader() throws IOException {
  byte[] header=new byte[]{0,0,MAGIC_HIGH,MAGIC_LOW,0,0,0,0,0,0,0,0,0};
  Channel channel=getServerSideChannel(url);
  ChannelBuffer buffer=ChannelBuffers.wrappedBuffer(header);
  Object obj=codec.decode(channel,buffer);
  Assert.assertEquals(TelnetCodec.DecodeResult.NEED_MORE_INPUT,obj);
  Assert.assertEquals(2,buffer.readerIndex());
}
