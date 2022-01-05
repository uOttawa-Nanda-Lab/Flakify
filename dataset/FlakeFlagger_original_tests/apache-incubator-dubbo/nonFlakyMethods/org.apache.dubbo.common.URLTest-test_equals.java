@Test public void test_equals() throws Exception {
  URL url1=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan");
  Map<String,String> params=new HashMap<String,String>();
  params.put("version","1.0.0");
  params.put("application","morgan");
  URL url2=new URL("dubbo","admin","hello1234","10.20.130.230",20880,"context/path",params);
  assertEquals(url1,url2);
}
