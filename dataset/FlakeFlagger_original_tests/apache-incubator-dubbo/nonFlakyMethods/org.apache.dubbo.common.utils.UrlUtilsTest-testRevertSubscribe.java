@Test public void testRevertSubscribe(){
  String key="perf/dubbo.test.api.HelloService:1.0.0";
  Map<String,String> subscribe=new HashMap<String,String>();
  subscribe.put(key,null);
  Map<String,String> newSubscribe=UrlUtils.revertSubscribe(subscribe);
  Map<String,String> expectSubscribe=new HashMap<String,String>();
  expectSubscribe.put("dubbo.test.api.HelloService","group=perf&version=1.0.0");
  assertEquals(expectSubscribe,newSubscribe);
}
