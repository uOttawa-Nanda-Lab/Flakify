@Test public void test_Path() throws Exception {
  URL url=new URL("dubbo","localhost",20880,"////path");
  assertEquals("path",url.getPath());
}
