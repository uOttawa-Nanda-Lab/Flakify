@Test public void testSubscribe(){
  String key="perf/dubbo.test.api.HelloService:1.0.0";
  Map<String,String> subscribe=new HashMap<String,String>();
  subscribe.put(key,null);
  Map<String,String> newSubscribe=UrlUtils.convertSubscribe(subscribe);
  assertEquals(subscribe,newSubscribe);
}
