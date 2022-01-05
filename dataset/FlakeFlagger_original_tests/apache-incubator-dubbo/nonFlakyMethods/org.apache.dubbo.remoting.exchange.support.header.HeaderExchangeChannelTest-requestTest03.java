@Test(expected=RemotingException.class) public void requestTest03() throws RemotingException {
  channel=new MockChannel(){
    @Override public void send(    Object req) throws RemotingException {
      throw new RemotingException(channel.getLocalAddress(),channel.getRemoteAddress(),"throw error");
    }
  }
;
  header=new HeaderExchangeChannel(channel);
  Object requestob=new Object();
  header.request(requestob,1000);
}
