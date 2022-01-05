@Test public void test_getAddress() throws Exception {
  URL url1=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan");
  assertEquals("10.20.130.230:20880",url1.getAddress());
}
