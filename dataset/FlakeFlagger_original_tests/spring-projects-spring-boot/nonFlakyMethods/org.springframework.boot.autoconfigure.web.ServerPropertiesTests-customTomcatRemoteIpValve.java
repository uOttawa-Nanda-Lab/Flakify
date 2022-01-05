@Test public void customTomcatRemoteIpValve() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put("server.tomcat.remote_ip_header","x-my-remote-ip-header");
  map.put("server.tomcat.protocol_header","x-my-protocol-header");
  map.put("server.tomcat.internal_proxies","192.168.0.1");
  map.put("server.tomcat.port-header","x-my-forward-port");
  bindProperties(map);
  TomcatEmbeddedServletContainerFactory container=new TomcatEmbeddedServletContainerFactory();
  this.properties.customize(container);
  assertEquals(1,container.getValves().size());
  Valve valve=container.getValves().iterator().next();
  assertThat(valve,instanceOf(RemoteIpValve.class));
  RemoteIpValve remoteIpValve=(RemoteIpValve)valve;
  assertEquals("x-my-protocol-header",remoteIpValve.getProtocolHeader());
  assertEquals("x-my-remote-ip-header",remoteIpValve.getRemoteIpHeader());
  assertEquals("x-my-forward-port",remoteIpValve.getPortHeader());
  assertEquals("192.168.0.1",remoteIpValve.getInternalProxies());
}
