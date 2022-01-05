@Test public void test_valueOf_Exception_noProtocol() throws Exception {
  try {
    URL.valueOf("://1.2.3.4:8080/path");
    fail();
  }
 catch (  IllegalStateException expected) {
    assertEquals("url missing protocol: \"://1.2.3.4:8080/path\"",expected.getMessage());
  }
}
