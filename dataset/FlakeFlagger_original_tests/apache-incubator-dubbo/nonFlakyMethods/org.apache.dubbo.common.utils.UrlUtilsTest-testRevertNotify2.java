@Test public void testRevertNotify2(){
  String key="perf/dubbo.test.api.HelloService:1.0.0";
  Map<String,Map<String,String>> notify=new HashMap<String,Map<String,String>>();
  Map<String,String> service=new HashMap<String,String>();
  service.put("dubbo://127.0.0.1:20880/com.xxx.XxxService","group=perf&version=1.0.0");
  notify.put(key,service);
  Map<String,Map<String,String>> newRegister=UrlUtils.revertNotify(notify);
  Map<String,Map<String,String>> expectedRegister=new HashMap<String,Map<String,String>>();
  service.put("dubbo://127.0.0.1:20880/com.xxx.XxxService","group=perf&version=1.0.0");
  expectedRegister.put("perf/dubbo.test.api.HelloService:1.0.0",service);
  assertEquals(expectedRegister,newRegister);
}
