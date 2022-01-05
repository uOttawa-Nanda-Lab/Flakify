@Test public void testServerClose() throws Exception {
  for (int i=0; i < 100; i++) {
    Server aServer=Exchangers.bind(URL.valueOf("exchange://localhost:" + (5000 + i) + "?server=netty3"),new TelnetServerHandler());
    aServer.close();
  }
}
