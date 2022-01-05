@Test(expected=RemotingException.class) public void test_Disconnect_Biz_Error() throws RemotingException {
  handler.disconnected(new MockedChannel());
}
