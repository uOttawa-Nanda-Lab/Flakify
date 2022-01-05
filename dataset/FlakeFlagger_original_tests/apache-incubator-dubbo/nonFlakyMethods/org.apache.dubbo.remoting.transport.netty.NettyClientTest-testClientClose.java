@Test public void testClientClose() throws Exception {
  List<ExchangeChannel> clients=new ArrayList<ExchangeChannel>(100);
  for (int i=0; i < 100; i++) {
    ExchangeChannel client=Exchangers.connect(URL.valueOf("exchange://localhost:10001?client=netty3"));
    Thread.sleep(5);
    clients.add(client);
  }
  for (  ExchangeChannel client : clients) {
    client.close();
  }
  Thread.sleep(1000);
}
