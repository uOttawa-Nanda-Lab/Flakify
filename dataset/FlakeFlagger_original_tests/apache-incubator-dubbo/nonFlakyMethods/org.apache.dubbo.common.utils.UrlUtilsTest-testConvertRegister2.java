@Test public void testConvertRegister2(){
  String key="dubbo.test.api.HelloService";
  Map<String,Map<String,String>> register=new HashMap<String,Map<String,String>>();
  Map<String,String> service=new HashMap<String,String>();
  service.put("dubbo://127.0.0.1:20880/com.xxx.XxxService","version=1.0.0&group=test&dubbo.version=2.0.0");
  register.put(key,service);
  Map<String,Map<String,String>> newRegister=UrlUtils.convertRegister(register);
  Map<String,String> newService=new HashMap<String,String>();
  newService.put("dubbo://127.0.0.1:20880/com.xxx.XxxService","dubbo.version=2.0.0&group=test&version=1.0.0");
  assertEquals(newService,newRegister.get("test/dubbo.test.api.HelloService:1.0.0"));
}
