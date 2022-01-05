@Test public void test_Localhost() throws Exception {
  URL url=URL.valueOf("dubbo://127.0.0.1:20880");
  assertEquals("127.0.0.1",url.getHost());
  assertTrue(url.isLocalHost());
  url=URL.valueOf("dubbo://127.0.1.1:20880");
  assertEquals("127.0.1.1",url.getHost());
  assertTrue(url.isLocalHost());
  url=URL.valueOf("dubbo://localhost:20880");
  assertEquals("localhost",url.getHost());
  assertTrue(url.isLocalHost());
}
