@Test public void ensureNoObjectsAreCreatedClientTest() throws ExecutionException, InterruptedException {
  List<OrbitStage> clients=new ArrayList<>();
  List<OrbitStage> servers=new ArrayList<OrbitStage>();
  Set<INodeAddress> serverAddresses=new HashSet<>();
  for (int i=0; i < 20; i++) {
    clients.add(createClient());
  }
  for (int i=0; i < 5; i++) {
    final OrbitStage server=createStage();
    servers.add(server);
    serverAddresses.add(server.getClusterPeer().localAddress());
  }
  for (int i=0; i < 50; i++) {
    final OrbitStage client=clients.get((int)(Math.random() * clients.size()));
    ISomeActor player=client.getReference(ISomeActor.class,String.valueOf(i));
    assertEquals("bla",player.sayHello("meh").get());
    assertTrue(serverAddresses.contains(client.getHosting().locateActor(player).join()));
  }
}
