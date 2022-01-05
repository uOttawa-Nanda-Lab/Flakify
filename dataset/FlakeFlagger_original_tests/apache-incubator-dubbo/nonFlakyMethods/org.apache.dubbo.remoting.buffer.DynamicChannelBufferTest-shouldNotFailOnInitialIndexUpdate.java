@Test public void shouldNotFailOnInitialIndexUpdate(){
  new DynamicChannelBuffer(10).setIndex(0,10);
}
