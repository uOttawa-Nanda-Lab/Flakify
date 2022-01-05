@Test public void testGetProperty() throws Exception {
  assertThat(ConfigUtils.getProperty("dubbo"),equalTo("properties"));
}
