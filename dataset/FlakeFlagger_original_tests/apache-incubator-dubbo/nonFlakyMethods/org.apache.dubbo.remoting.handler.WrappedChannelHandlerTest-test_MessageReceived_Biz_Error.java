@Test(expected=RemotingException.class) public void test_MessageReceived_Biz_Error() throws RemotingException {
  handler.received(new MockedChannel(),"");
}
