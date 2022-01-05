@Test public void getOrAddChannelTest01(){
  channel=new MockChannel(){
    @Override public URL getUrl(){
      return url;
    }
    @Override public boolean isConnected(){
      return true;
    }
  }
;
  Assert.assertNull(channel.getAttribute(CHANNEL_KEY));
  HeaderExchangeChannel ret=HeaderExchangeChannel.getOrAddChannel(channel);
  Assert.assertNotNull(ret);
  Assert.assertNotNull(channel.getAttribute(CHANNEL_KEY));
  Assert.assertEquals(channel.getAttribute(CHANNEL_KEY).getClass(),HeaderExchangeChannel.class);
}
