public void testString2Parameters() throws Exception {
  String qs="name=org.apache.dubbo.rpc.service.GenericService&version=1.0.15&lb=lcr";
  Map<String,String> map=StringUtils.parseQueryString(qs);
  assertEquals(map.get("name"),ServiceName);
  assertEquals(map.get("version"),ServiceVersion);
  assertEquals(map.get("lb"),LoadBalance);
}
