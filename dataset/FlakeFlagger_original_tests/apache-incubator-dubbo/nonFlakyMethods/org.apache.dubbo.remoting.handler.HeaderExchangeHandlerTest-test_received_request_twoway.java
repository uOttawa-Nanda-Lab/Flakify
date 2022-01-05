@Test public void test_received_request_twoway() throws RemotingException {
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
      Assert.assertEquals(Response.OK,res.getStatus());
      Assert.assertEquals(requestdata,res.getResult());
      Assert.assertEquals(null,res.getErrorMessage());
      count.incrementAndGet();
    }
  }
;
  ExchangeHandler exhandler=new MockedExchangeHandler(){
    @Override public CompletableFuture<Object> reply(    ExchangeChannel channel,    Object request) throws RemotingException {
      return CompletableFuture.completedFuture(request);
    }
    @Override public void received(    Channel channel,    Object message) throws RemotingException {
      Assert.fail();
    }
  }
;
  HeaderExchangeHandler hexhandler=new HeaderExchangeHandler(exhandler);
  hexhandler.received(mchannel,request);
  Assert.assertEquals(1,count.get());
}
