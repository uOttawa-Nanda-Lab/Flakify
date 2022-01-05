@Test public void test_Disconnect_Biz_Error() throws RemotingException {
  handler=new ConnectionOrderedChannelHandler(new BizChannelHander(true),url);
  handler.disconnected(new MockedChannel());
}
