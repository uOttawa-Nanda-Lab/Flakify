@Test public void testLoadProperties() throws Exception {
  Properties p=ConfigUtils.loadProperties("dubbo.properties");
  assertThat((String)p.get("dubbo"),equalTo("properties"));
}
