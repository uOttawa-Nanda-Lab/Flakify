@Test public void sendTest04() throws RemotingException {
  String message="this is a test message";
  header.send(message);
  List<Object> objects=channel.getSentObjects();
  Assert.assertEquals(objects.get(0),"this is a test message");
}
