@Test @SuppressWarnings("unchecked") public void testOverride() throws Exception {
  this.context.register(Other.class);
  EnvironmentTestUtils.addEnvironment(this.context,"other.name:foo");
  this.context.refresh();
  ConfigurationPropertiesReportEndpoint report=this.context.getBean(ConfigurationPropertiesReportEndpoint.class);
  Map<String,Object> properties=report.invoke();
  Map<String,Object> nestedProperties=(Map<String,Object>)properties.get("bar");
  assertNotNull(nestedProperties);
  assertEquals("other",nestedProperties.get("prefix"));
  assertNotNull(nestedProperties.get("properties"));
}
