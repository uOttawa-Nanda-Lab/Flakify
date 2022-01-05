@Test public void closeWithTimeoutTest02(){
  Assert.assertFalse(channel.isClosed());
  Request request=new Request();
  DefaultFuture.newFuture(channel,request,100);
  header.close(100);
  header.close(1000);
}
