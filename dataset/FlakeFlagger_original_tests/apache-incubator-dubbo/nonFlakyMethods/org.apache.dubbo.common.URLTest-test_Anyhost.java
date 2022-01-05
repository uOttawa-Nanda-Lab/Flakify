@Test public void test_Anyhost() throws Exception {
  URL url=URL.valueOf("dubbo://0.0.0.0:20880");
  assertEquals("0.0.0.0",url.getHost());
  assertTrue(url.isAnyHost());
}
