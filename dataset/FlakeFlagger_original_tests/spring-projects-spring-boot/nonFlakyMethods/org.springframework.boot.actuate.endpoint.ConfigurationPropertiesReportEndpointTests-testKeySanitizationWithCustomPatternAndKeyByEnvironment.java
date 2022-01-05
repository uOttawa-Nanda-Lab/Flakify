@SuppressWarnings("unchecked") @Test public void testKeySanitizationWithCustomPatternAndKeyByEnvironment() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"endpoints.configprops.keys-to-sanitize: .*pass.*, property");
  this.context.register(Config.class);
  this.context.refresh();
  ConfigurationPropertiesReportEndpoint report=getEndpointBean();
  Map<String,Object> properties=report.invoke();
  Map<String,Object> nestedProperties=(Map<String,Object>)((Map<String,Object>)properties.get("testProperties")).get("properties");
  assertNotNull(nestedProperties);
  assertEquals("******",nestedProperties.get("dbPassword"));
  assertEquals("******",nestedProperties.get("myTestProperty"));
}
