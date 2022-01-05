@Test @SuppressWarnings("unchecked") public void testNaming() throws Exception {
  this.context.register(Config.class);
  EnvironmentTestUtils.addEnvironment(this.context,"other.name:foo","first.name:bar");
  this.context.refresh();
  ConfigurationPropertiesReportEndpoint report=this.context.getBean(ConfigurationPropertiesReportEndpoint.class);
  Map<String,Object> properties=report.invoke();
  Map<String,Object> nestedProperties=(Map<String,Object>)properties.get("other");
  assertNotNull(nestedProperties);
  assertEquals("other",nestedProperties.get("prefix"));
  assertNotNull(nestedProperties.get("properties"));
}
