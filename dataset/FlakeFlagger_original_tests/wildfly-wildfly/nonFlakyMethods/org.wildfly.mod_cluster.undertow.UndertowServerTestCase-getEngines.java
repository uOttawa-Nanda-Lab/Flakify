@Test public void getEngines(){
  when(configuration.getServer()).thenReturn(mock(org.wildfly.extension.undertow.Server.class));
  when(configuration.getServer().getName()).thenReturn(undertowServer.getName());
  Iterator<Engine> engines=this.server.getEngines().iterator();
  assertTrue(engines.hasNext());
  Engine engine=engines.next();
  assertSame(this.connector,engine.getProxyConnector());
  assertFalse(engines.hasNext());
}
