@Test public void testRevertForbid3(){
  String service1="dubbo.test.api.HelloService:1.0.0";
  String service2="dubbo.test.api.HelloService:2.0.0";
  List<String> forbid=new ArrayList<String>();
  forbid.add(service1);
  forbid.add(service2);
  List<String> newForbid=UrlUtils.revertForbid(forbid,null);
  assertEquals(forbid,newForbid);
}
