@Test public void test_received_request_oneway() throws RemotingException {
  final Channel mchannel=new MockedChannel();
  final Person requestdata=new Person("charles");
  Request request=new Request();
  request.setTwoWay(false);
  request.setData(requestdata);
  ExchangeHandler exhandler=new MockedExchangeHandler(){
    @Override public void received(    Channel channel,    Object message) throws RemotingException {
      Assert.assertEquals(requestdata,message);
    }
  }
;
  HeaderExchangeHandler hexhandler=new HeaderExchangeHandler(exhandler);
  hexhandler.received(mchannel,request);
}
