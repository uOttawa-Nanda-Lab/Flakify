@Test public void shouldNotFailOnInitialIndexUpdate2(){
  new DynamicChannelBuffer(10).writerIndex(10);
}
