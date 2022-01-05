@Test public void testReconnect() throws RemotingException, InterruptedException {
{
    int port=NetUtils.getAvailablePort();
    Client client=startClient(port,200);
    Assert.assertEquals(false,client.isConnected());
    Server server=startServer(port);
    for (int i=0; i < 100 && !client.isConnected(); i++) {
      Thread.sleep(10);
    }
    Assert.assertEquals(true,client.isConnected());
    client.close(2000);
    server.close(2000);
  }
{
    int port=NetUtils.getAvailablePort();
    Client client=startClient(port,20000);
    Assert.assertEquals(false,client.isConnected());
    Server server=startServer(port);
    for (int i=0; i < 5; i++) {
      Thread.sleep(200);
    }
    Assert.assertEquals(false,client.isConnected());
    client.close(2000);
    server.close(2000);
  }
}
