@Test public void getOrAddChannelTest02(){
  channel=null;
  HeaderExchangeChannel ret=HeaderExchangeChannel.getOrAddChannel(channel);
  Assert.assertNull(ret);
}
