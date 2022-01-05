@Test public void sendTest02() throws RemotingException {
  boolean sent=true;
  int message=1;
  header.send(message,sent);
  List<Object> objects=channel.getSentObjects();
  Assert.assertEquals(objects.get(0).getClass(),Request.class);
  Request request=(Request)objects.get(0);
  Assert.assertEquals(request.getVersion(),"2.0.2");
}
