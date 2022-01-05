@Test public void test_Caught_Biz_Error() throws RemotingException {
  try {
    handler.caught(new MockedChannel(),new BizException());
    fail();
  }
 catch (  Exception e) {
    Assert.assertEquals(BizException.class,e.getCause().getClass());
  }
}
