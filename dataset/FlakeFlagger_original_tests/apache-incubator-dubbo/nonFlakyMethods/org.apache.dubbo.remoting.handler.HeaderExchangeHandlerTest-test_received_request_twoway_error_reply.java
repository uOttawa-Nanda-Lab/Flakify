@Test public void test_received_request_twoway_error_reply() throws RemotingException {
  final Person requestdata=new Person("charles");
  final Request request=new Request();
  request.setTwoWay(true);
  request.setData(requestdata);
  final AtomicInteger count=new AtomicInteger(0);
  final Channel mchannel=new MockedChannel(){
    @Override public void send(    Object message) throws RemotingException {
      Response res=(Response)message;
      Assert.assertEquals(request.getId(),res.getId());
      Assert.assertEquals(request.getVersion(),res.getVersion());
      Assert.assertEquals(Response.SERVICE_ERROR,res.getStatus());
      Assert.assertNull(res.getResult());
      Assert.assertTrue(res.getErrorMessage().contains(BizException.class.getName()));
      count.incrementAndGet();
    }
  }
;
  ExchangeHandler exhandler=new MockedExchangeHandler(){
    @Override public CompletableFuture<Object> reply(    ExchangeChannel channel,    Object request) throws RemotingException {
      throw new BizException();
    }
  }
;
  HeaderExchangeHandler hexhandler=new HeaderExchangeHandler(exhandler);
  hexhandler.received(mchannel,request);
  Assert.assertEquals(1,count.get());
}
