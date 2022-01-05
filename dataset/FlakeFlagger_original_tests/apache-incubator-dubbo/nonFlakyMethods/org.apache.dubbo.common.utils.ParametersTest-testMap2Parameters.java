public void testMap2Parameters() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put("name","org.apache.dubbo.rpc.service.GenericService");
  map.put("version","1.0.15");
  map.put("lb","lcr");
  map.put("max.active","500");
  assertEquals(map.get("name"),ServiceName);
  assertEquals(map.get("version"),ServiceVersion);
  assertEquals(map.get("lb"),LoadBalance);
}
