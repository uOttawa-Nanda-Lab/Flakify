@Test public void disableTomcatRemoteIpValve() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put("server.tomcat.remote_ip_header","");
  map.put("server.tomcat.protocol_header","");
  bindProperties(map);
  TomcatEmbeddedServletContainerFactory container=new TomcatEmbeddedServletContainerFactory();
  this.properties.customize(container);
  assertEquals(0,container.getValves().size());
}
