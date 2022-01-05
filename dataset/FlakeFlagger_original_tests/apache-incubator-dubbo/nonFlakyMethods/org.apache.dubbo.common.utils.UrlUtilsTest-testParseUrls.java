@Test public void testParseUrls(){
  String addresses="192.168.0.1|192.168.0.2|192.168.0.3";
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put("username","root");
  parameters.put("password","alibaba");
  parameters.put("port","10000");
  parameters.put("protocol","dubbo");
  List<URL> urls=UrlUtils.parseURLs(addresses,parameters);
  assertEquals("192.168.0.1" + ":10000",urls.get(0).getAddress());
  assertEquals("192.168.0.2" + ":10000",urls.get(1).getAddress());
}
