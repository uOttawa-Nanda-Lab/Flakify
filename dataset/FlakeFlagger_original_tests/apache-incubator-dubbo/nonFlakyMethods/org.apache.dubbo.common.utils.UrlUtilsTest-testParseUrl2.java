@Test public void testParseUrl2(){
  String address="192.168.0.1";
  String backupAddress1="192.168.0.2";
  String backupAddress2="192.168.0.3";
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put("username","root");
  parameters.put("password","alibaba");
  parameters.put("port","10000");
  parameters.put("protocol","dubbo");
  URL url=UrlUtils.parseURL(address + "," + backupAddress1+ ","+ backupAddress2,parameters);
  assertEquals("192.168.0.1:10000",url.getAddress());
  assertEquals("root",url.getUsername());
  assertEquals("alibaba",url.getPassword());
  assertEquals(10000,url.getPort());
  assertEquals("dubbo",url.getProtocol());
  assertEquals("192.168.0.2" + "," + "192.168.0.3",url.getParameter("backup"));
}
