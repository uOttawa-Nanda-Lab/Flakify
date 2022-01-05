@Test public void testCustomizeUriEncoding() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put("server.tomcat.uriEncoding","US-ASCII");
  bindProperties(map);
  assertEquals("US-ASCII",this.properties.getTomcat().getUriEncoding());
}
