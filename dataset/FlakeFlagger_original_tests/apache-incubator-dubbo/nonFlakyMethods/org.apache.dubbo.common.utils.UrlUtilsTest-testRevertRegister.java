@Test public void testRevertRegister(){
  String key="perf/dubbo.test.api.HelloService:1.0.0";
  Map<String,Map<String,String>> register=new HashMap<String,Map<String,String>>();
  Map<String,String> service=new HashMap<String,String>();
  service.put("dubbo://127.0.0.1:20880/com.xxx.XxxService",null);
  register.put(key,service);
  Map<String,Map<String,String>> newRegister=UrlUtils.revertRegister(register);
  Map<String,Map<String,String>> expectedRegister=new HashMap<String,Map<String,String>>();
  service.put("dubbo://127.0.0.1:20880/com.xxx.XxxService","group=perf&version=1.0.0");
  expectedRegister.put("dubbo.test.api.HelloService",service);
  assertEquals(expectedRegister,newRegister);
}
