@Test public void testGetPropertyFromSystem() throws Exception {
  try {
    System.setProperty("dubbo","system");
    assertThat(ConfigUtils.getProperty("dubbo"),equalTo("system"));
  }
  finally {
    System.clearProperty("dubbo");
  }
}
