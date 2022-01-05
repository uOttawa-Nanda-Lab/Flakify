@Test public void test_javaNetUrl() throws Exception {
  java.net.URL url=new java.net.URL("http://admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan#anchor1");
  assertEquals("http",url.getProtocol());
  assertEquals("admin:hello1234",url.getUserInfo());
  assertEquals("10.20.130.230",url.getHost());
  assertEquals(20880,url.getPort());
  assertEquals("/context/path",url.getPath());
  assertEquals("version=1.0.0&application=morgan",url.getQuery());
  assertEquals("anchor1",url.getRef());
  assertEquals("admin:hello1234@10.20.130.230:20880",url.getAuthority());
  assertEquals("/context/path?version=1.0.0&application=morgan",url.getFile());
}
