@Test public void testConvertRegister(){
  String key="perf/dubbo.test.api.HelloService:1.0.0";
  Map<String,Map<String,String>> register=new HashMap<String,Map<String,String>>();
  register.put(key,null);
  Map<String,Map<String,String>> newRegister=UrlUtils.convertRegister(register);
  assertEquals(register,newRegister);
}
