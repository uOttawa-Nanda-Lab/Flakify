@Test public void testParseFromParameter(){
  String address="127.0.0.1";
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put("username","root");
  parameters.put("password","alibaba");
  parameters.put("port","10000");
  parameters.put("protocol","dubbo");
  parameters.put("path","dubbo.test.api");
  parameters.put("aaa","bbb");
  parameters.put("ccc","ddd");
  URL url=UrlUtils.parseURL(address,parameters);
  assertEquals(localAddress + ":10000",url.getAddress());
  assertEquals("root",url.getUsername());
  assertEquals("alibaba",url.getPassword());
  assertEquals(10000,url.getPort());
  assertEquals("dubbo",url.getProtocol());
  assertEquals("dubbo.test.api",url.getPath());
  assertEquals("bbb",url.getParameter("aaa"));
  assertEquals("ddd",url.getParameter("ccc"));
}
