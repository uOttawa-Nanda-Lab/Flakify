@Test public void testTomcatBinding() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put("server.tomcat.access_log_pattern","%h %t '%r' %s %b");
  map.put("server.tomcat.protocol_header","X-Forwarded-Protocol");
  map.put("server.tomcat.remote_ip_header","Remote-Ip");
  map.put("server.tomcat.internal_proxies","10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
  bindProperties(map);
  assertEquals("%h %t '%r' %s %b",this.properties.getTomcat().getAccessLogPattern());
  assertEquals("Remote-Ip",this.properties.getTomcat().getRemoteIpHeader());
  assertEquals("X-Forwarded-Protocol",this.properties.getTomcat().getProtocolHeader());
  assertEquals("10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}",this.properties.getTomcat().getInternalProxies());
}
