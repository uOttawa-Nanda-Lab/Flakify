@Test(expected=RemotingException.class) public void test_Connect_Biz_Error() throws RemotingException {
  handler.connected(new MockedChannel());
}
