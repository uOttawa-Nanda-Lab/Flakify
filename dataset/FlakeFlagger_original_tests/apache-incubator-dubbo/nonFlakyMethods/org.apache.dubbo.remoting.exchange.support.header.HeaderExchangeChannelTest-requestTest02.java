@Test public void requestTest02() throws RemotingException {
  Channel channel=Mockito.mock(MockChannel.class);
  header=new HeaderExchangeChannel(channel);
  when(channel.getUrl()).thenReturn(url);
  Object requestob=new Object();
  header.request(requestob);
  ArgumentCaptor<Request> argumentCaptor=ArgumentCaptor.forClass(Request.class);
  verify(channel,times(1)).send(argumentCaptor.capture());
  Assert.assertEquals(argumentCaptor.getValue().getData(),requestob);
}
