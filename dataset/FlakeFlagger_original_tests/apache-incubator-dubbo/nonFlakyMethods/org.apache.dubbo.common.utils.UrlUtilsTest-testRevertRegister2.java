@Test public void testRevertRegister2(){
  String key="dubbo.test.api.HelloService";
  Map<String,Map<String,String>> register=new HashMap<String,Map<String,String>>();
  Map<String,String> service=new HashMap<String,String>();
  service.put("dubbo://127.0.0.1:20880/com.xxx.XxxService",null);
  register.put(key,service);
  Map<String,Map<String,String>> newRegister=UrlUtils.revertRegister(register);
  Map<String,Map<String,String>> expectedRegister=new HashMap<String,Map<String,String>>();
  service.put("dubbo://127.0.0.1:20880/com.xxx.XxxService",null);
  expectedRegister.put("dubbo.test.api.HelloService",service);
  assertEquals(expectedRegister,newRegister);
}
