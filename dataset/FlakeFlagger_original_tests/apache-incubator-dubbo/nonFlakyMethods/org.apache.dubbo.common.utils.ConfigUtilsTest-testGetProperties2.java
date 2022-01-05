@Test public void testGetProperties2() throws Exception {
  System.clearProperty(Constants.DUBBO_PROPERTIES_KEY);
  Properties p=ConfigUtils.getProperties();
  assertThat((String)p.get("dubbo"),equalTo("properties"));
}
