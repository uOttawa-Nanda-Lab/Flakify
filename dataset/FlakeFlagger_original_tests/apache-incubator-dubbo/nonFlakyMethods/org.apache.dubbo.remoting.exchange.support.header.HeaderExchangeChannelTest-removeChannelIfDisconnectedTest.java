@Test public void removeChannelIfDisconnectedTest(){
  Assert.assertNull(channel.getAttribute(CHANNEL_KEY));
  channel.setAttribute(CHANNEL_KEY,header);
  channel.close();
  HeaderExchangeChannel.removeChannelIfDisconnected(channel);
  Assert.assertNull(channel.getAttribute(CHANNEL_KEY));
}
