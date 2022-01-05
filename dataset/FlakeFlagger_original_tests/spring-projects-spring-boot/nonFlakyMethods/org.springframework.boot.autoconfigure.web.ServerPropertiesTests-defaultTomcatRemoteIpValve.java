@Test public void defaultTomcatRemoteIpValve() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put("server.tomcat.protocol_header","x-forwarded-proto");
  map.put("server.tomcat.remote_ip_header","x-forwarded-for");
  bindProperties(map);
  TomcatEmbeddedServletContainerFactory container=new TomcatEmbeddedServletContainerFactory();
  this.properties.customize(container);
  assertEquals(1,container.getValves().size());
  Valve valve=container.getValves().iterator().next();
  assertThat(valve,instanceOf(RemoteIpValve.class));
  RemoteIpValve remoteIpValve=(RemoteIpValve)valve;
  assertEquals("x-forwarded-proto",remoteIpValve.getProtocolHeader());
  assertEquals("x-forwarded-for",remoteIpValve.getRemoteIpHeader());
  String expectedInternalProxies="10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|" + "192\\.168\\.\\d{1,3}\\.\\d{1,3}|" + "169\\.254\\.\\d{1,3}\\.\\d{1,3}|"+ "127\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
  assertEquals(expectedInternalProxies,remoteIpValve.getInternalProxies());
}
