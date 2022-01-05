@Test public void testGetPropertyDefaultValue() throws Exception {
  assertThat(ConfigUtils.getProperty("not-exist","default"),equalTo("default"));
}
