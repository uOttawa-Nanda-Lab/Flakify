@Test public void test_received_request_event_other_discard() throws RemotingException {
  final Request request=new Request();
  request.setTwoWay(true);
  request.setEvent("my event");
  final Channel mchannel=new MockedChannel(){
    @Override public void send(    Object message) throws RemotingException {
      Assert.fail();
    }
  }
;
  HeaderExchangeHandler hexhandler=new HeaderExchangeHandler(new MockedExchangeHandler(){
    @Override public CompletableFuture reply(    ExchangeChannel channel,    Object request) throws RemotingException {
      Assert.fail();
      throw new RemotingException(channel,"");
    }
    @Override public void received(    Channel channel,    Object message) throws RemotingException {
      Assert.fail();
      throw new RemotingException(channel,"");
    }
  }
);
  hexhandler.received(mchannel,request);
}
