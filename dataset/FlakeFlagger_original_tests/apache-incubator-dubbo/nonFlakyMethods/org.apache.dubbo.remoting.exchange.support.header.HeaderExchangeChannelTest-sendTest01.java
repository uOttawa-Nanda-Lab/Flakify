@Test public void sendTest01() throws RemotingException {
  boolean sent=true;
  String message="this is a test message";
  header.send(message,sent);
  List<Object> objects=channel.getSentObjects();
  Assert.assertEquals(objects.get(0),"this is a test message");
}
