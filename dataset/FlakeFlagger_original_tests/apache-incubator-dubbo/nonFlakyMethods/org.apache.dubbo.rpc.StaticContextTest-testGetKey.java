@Test public void testGetKey(){
  String interfaceName="interface";
  String method="method";
  String group="group";
  String version="1.0";
  String suffix="suffix";
  Map<String,String> para=new HashMap<>();
  para.put(Constants.INTERFACE_KEY,interfaceName);
  para.put(Constants.GROUP_KEY,group);
  para.put(Constants.VERSION_KEY,version);
  URL url=new URL("dubbo","localhost",20880,interfaceName,para);
  Assert.assertEquals(StaticContext.getKey(url,method,suffix),StaticContext.getKey(para,method,suffix));
}
