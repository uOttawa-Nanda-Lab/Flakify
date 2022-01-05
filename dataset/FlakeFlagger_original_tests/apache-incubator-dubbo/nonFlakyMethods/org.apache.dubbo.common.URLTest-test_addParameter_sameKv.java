@Test public void test_addParameter_sameKv() throws Exception {
  URL url=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?application=morgan&k1=v1");
  URL newUrl=url.addParameter("k1","v1");
  assertSame(newUrl,url);
}
