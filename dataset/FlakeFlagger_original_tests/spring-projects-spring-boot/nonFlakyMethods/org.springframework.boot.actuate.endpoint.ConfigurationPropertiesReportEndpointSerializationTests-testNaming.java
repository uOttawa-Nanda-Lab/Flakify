@Test @SuppressWarnings("unchecked") public void testNaming() throws Exception {
  this.context.register(Config.class);
  EnvironmentTestUtils.addEnvironment(this.context,"foo.name:foo");
  this.context.refresh();
  ConfigurationPropertiesReportEndpoint report=this.context.getBean(ConfigurationPropertiesReportEndpoint.class);
  Map<String,Object> properties=report.invoke();
  Map<String,Object> nestedProperties=(Map<String,Object>)properties.get("foo");
  assertNotNull(nestedProperties);
  assertEquals("foo",nestedProperties.get("prefix"));
  Map<String,Object> map=(Map<String,Object>)nestedProperties.get("properties");
  assertNotNull(map);
  assertEquals(1,map.size());
  assertEquals("foo",map.get("name"));
}
