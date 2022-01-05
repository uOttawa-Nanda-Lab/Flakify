@Test public void testGetSystemProperty() throws Exception {
  try {
    System.setProperty("dubbo","system-only");
    assertThat(ConfigUtils.getSystemProperty("dubbo"),equalTo("system-only"));
  }
  finally {
    System.clearProperty("dubbo");
  }
}
