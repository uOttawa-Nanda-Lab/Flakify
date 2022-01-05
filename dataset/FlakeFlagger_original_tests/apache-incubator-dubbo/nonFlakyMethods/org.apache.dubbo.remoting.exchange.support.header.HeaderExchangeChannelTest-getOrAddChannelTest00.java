@Test public void getOrAddChannelTest00(){
  channel.setAttribute("CHANNEL_KEY","attribute");
  HeaderExchangeChannel ret=HeaderExchangeChannel.getOrAddChannel(channel);
  Assert.assertNotNull(ret);
}
