@Test public void testCustomizeTomcatHeaderSize() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put("server.tomcat.maxHttpHeaderSize","9999");
  bindProperties(map);
  assertEquals(9999,this.properties.getTomcat().getMaxHttpHeaderSize());
}
