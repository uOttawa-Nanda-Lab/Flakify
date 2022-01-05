@Test public void test_Caught_Biz_Error() throws RemotingException {
  handler.caught(new MockedChannel(),new BizException());
}
