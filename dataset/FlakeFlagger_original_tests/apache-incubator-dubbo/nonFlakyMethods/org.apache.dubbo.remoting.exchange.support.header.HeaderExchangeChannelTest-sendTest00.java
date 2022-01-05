@Test public void sendTest00(){
  boolean sent=true;
  String message="this is a test message";
  try {
    header.close(1);
    header.send(message,sent);
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof RemotingException);
  }
}
