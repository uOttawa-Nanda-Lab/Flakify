@SuppressWarnings("unchecked") @Test public void testKeySanitizationWithCustomPattern() throws Exception {
  ConfigurationPropertiesReportEndpoint report=getEndpointBean();
  report.setKeysToSanitize(".*pass.*");
  Map<String,Object> properties=report.invoke();
  Map<String,Object> nestedProperties=(Map<String,Object>)((Map<String,Object>)properties.get("testProperties")).get("properties");
  assertNotNull(nestedProperties);
  assertEquals("******",nestedProperties.get("dbPassword"));
  assertEquals("654321",nestedProperties.get("myTestProperty"));
}
