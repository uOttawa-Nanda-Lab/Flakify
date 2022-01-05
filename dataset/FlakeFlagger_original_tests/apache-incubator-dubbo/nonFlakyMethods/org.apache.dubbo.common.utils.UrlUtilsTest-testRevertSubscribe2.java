@Test public void testRevertSubscribe2(){
  String key="dubbo.test.api.HelloService";
  Map<String,String> subscribe=new HashMap<String,String>();
  subscribe.put(key,null);
  Map<String,String> newSubscribe=UrlUtils.revertSubscribe(subscribe);
  assertEquals(subscribe,newSubscribe);
}
