@Test public void test_Connect_Biz_Error() throws RemotingException {
  handler=new ConnectionOrderedChannelHandler(new BizChannelHander(true),url);
  handler.connected(new MockedChannel());
}
