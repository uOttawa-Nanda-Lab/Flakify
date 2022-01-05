@SuppressWarnings("unchecked") @Test public void testKeySanitizationWithCustomPatternByEnvironment() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"endpoints.configprops.keys-to-sanitize: .*pass.*");
  this.context.register(Config.class);
  this.context.refresh();
  ConfigurationPropertiesReportEndpoint report=getEndpointBean();
  Map<String,Object> properties=report.invoke();
  Map<String,Object> nestedProperties=(Map<String,Object>)((Map<String,Object>)properties.get("testProperties")).get("properties");
  assertNotNull(nestedProperties);
  assertEquals("******",nestedProperties.get("dbPassword"));
  assertEquals("654321",nestedProperties.get("myTestProperty"));
}
