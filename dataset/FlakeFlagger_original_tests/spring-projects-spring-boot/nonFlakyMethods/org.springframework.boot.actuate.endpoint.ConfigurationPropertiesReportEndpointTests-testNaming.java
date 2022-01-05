@Test @SuppressWarnings("unchecked") public void testNaming() throws Exception {
  ConfigurationPropertiesReportEndpoint report=getEndpointBean();
  Map<String,Object> properties=report.invoke();
  Map<String,Object> nestedProperties=(Map<String,Object>)properties.get("testProperties");
  assertNotNull(nestedProperties);
  assertEquals("test",nestedProperties.get("prefix"));
  assertNotNull(nestedProperties.get("properties"));
}
