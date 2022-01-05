@Test public void testSubscribe2(){
  String key="dubbo.test.api.HelloService";
  Map<String,String> subscribe=new HashMap<String,String>();
  subscribe.put(key,"version=1.0.0&group=test&dubbo.version=2.0.0");
  Map<String,String> newSubscribe=UrlUtils.convertSubscribe(subscribe);
  assertEquals("dubbo.version=2.0.0&group=test&version=1.0.0",newSubscribe.get("test/dubbo.test.api.HelloService:1.0.0"));
}
