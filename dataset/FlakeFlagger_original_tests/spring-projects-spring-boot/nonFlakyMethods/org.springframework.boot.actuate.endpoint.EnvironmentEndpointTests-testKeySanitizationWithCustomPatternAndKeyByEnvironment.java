@SuppressWarnings("unchecked") @Test public void testKeySanitizationWithCustomPatternAndKeyByEnvironment() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"endpoints.env.keys-to-sanitize: .*pass.*, key");
  this.context.register(Config.class);
  this.context.refresh();
  System.setProperty("dbPassword","123456");
  System.setProperty("apiKey","123456");
  EnvironmentEndpoint report=getEndpointBean();
  Map<String,Object> env=report.invoke();
  assertEquals("******",((Map<String,Object>)env.get("systemProperties")).get("dbPassword"));
  assertEquals("******",((Map<String,Object>)env.get("systemProperties")).get("apiKey"));
}
