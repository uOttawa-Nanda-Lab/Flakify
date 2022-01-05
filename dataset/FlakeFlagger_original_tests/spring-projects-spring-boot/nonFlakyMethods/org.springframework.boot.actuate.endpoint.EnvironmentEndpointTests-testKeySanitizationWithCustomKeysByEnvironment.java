@SuppressWarnings("unchecked") @Test public void testKeySanitizationWithCustomKeysByEnvironment() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"endpoints.env.keys-to-sanitize: key");
  this.context.register(Config.class);
  this.context.refresh();
  System.setProperty("dbPassword","123456");
  System.setProperty("apiKey","123456");
  EnvironmentEndpoint report=getEndpointBean();
  Map<String,Object> env=report.invoke();
  assertEquals("123456",((Map<String,Object>)env.get("systemProperties")).get("dbPassword"));
  assertEquals("******",((Map<String,Object>)env.get("systemProperties")).get("apiKey"));
}
