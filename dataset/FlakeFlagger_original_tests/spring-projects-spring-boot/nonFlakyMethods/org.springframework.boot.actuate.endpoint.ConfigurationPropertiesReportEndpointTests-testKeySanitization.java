@Test @SuppressWarnings("unchecked") public void testKeySanitization() throws Exception {
  ConfigurationPropertiesReportEndpoint report=getEndpointBean();
  report.setKeysToSanitize("property");
  Map<String,Object> properties=report.invoke();
  Map<String,Object> nestedProperties=(Map<String,Object>)((Map<String,Object>)properties.get("testProperties")).get("properties");
  assertNotNull(nestedProperties);
  assertEquals("123456",nestedProperties.get("dbPassword"));
  assertEquals("******",nestedProperties.get("myTestProperty"));
}
