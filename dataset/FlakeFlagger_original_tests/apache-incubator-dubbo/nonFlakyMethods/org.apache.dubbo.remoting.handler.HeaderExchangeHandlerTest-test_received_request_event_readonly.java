@Test public void test_received_request_event_readonly() throws RemotingException {
  final Request request=new Request();
  request.setTwoWay(true);
  request.setEvent(Request.READONLY_EVENT);
  final Channel mchannel=new MockedChannel();
  HeaderExchangeHandler hexhandler=new HeaderExchangeHandler(new MockedExchangeHandler());
  hexhandler.received(mchannel,request);
  Assert.assertTrue(mchannel.hasAttribute(Constants.CHANNEL_ATTRIBUTE_READONLY_KEY));
}
