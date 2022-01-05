@SuppressWarnings("unchecked") @Test public void testKeySanitization() throws Exception {
  System.setProperty("dbPassword","123456");
  System.setProperty("apiKey","123456");
  EnvironmentEndpoint report=getEndpointBean();
  Map<String,Object> env=report.invoke();
  assertEquals("******",((Map<String,Object>)env.get("systemProperties")).get("dbPassword"));
  assertEquals("******",((Map<String,Object>)env.get("systemProperties")).get("apiKey"));
}
