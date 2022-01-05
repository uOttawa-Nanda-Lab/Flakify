@Test public void testIsDead(){
  Set<String> deadServers=new HashSet<String>();
  final String hostname123="one,123,3";
  assertFalse(ServerManager.isDead(deadServers,hostname123,false));
  assertFalse(ServerManager.isDead(deadServers,hostname123,true));
  deadServers.add(hostname123);
  assertTrue(ServerManager.isDead(deadServers,hostname123,false));
  assertFalse(ServerManager.isDead(deadServers,"one:1",true));
  assertFalse(ServerManager.isDead(deadServers,"one:1234",true));
  assertTrue(ServerManager.isDead(deadServers,"one:123",true));
}
