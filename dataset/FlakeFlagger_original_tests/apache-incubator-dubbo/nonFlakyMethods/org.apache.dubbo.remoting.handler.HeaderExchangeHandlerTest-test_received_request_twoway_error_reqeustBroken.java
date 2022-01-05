@Test public void test_received_request_twoway_error_reqeustBroken() throws RemotingException {
  final Request request=new Request();
  request.setTwoWay(true);
  request.setData(new BizException());
  request.setBroken(true);
  final AtomicInteger count=new AtomicInteger(0);
  final Channel mchannel=new MockedChannel(){
    @Override public void send(    Object message) throws RemotingException {
      Response res=(Response)message;
      Assert.assertEquals(request.getId(),res.getId());
      Assert.assertEquals(request.getVersion(),res.getVersion());
      Assert.assertEquals(Response.BAD_REQUEST,res.getStatus());
      Assert.assertNull(res.getResult());
      Assert.assertTrue(res.getErrorMessage().contains(BizException.class.getName()));
      count.incrementAndGet();
    }
  }
;
  HeaderExchangeHandler hexhandler=new HeaderExchangeHandler(new MockedExchangeHandler());
  hexhandler.received(mchannel,request);
  Assert.assertEquals(1,count.get());
}
