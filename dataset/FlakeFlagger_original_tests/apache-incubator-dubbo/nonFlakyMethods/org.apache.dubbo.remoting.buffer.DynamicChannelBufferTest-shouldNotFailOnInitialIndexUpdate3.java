@Test public void shouldNotFailOnInitialIndexUpdate3(){
  ChannelBuffer buf=new DynamicChannelBuffer(10);
  buf.writerIndex(10);
  buf.readerIndex(10);
}
