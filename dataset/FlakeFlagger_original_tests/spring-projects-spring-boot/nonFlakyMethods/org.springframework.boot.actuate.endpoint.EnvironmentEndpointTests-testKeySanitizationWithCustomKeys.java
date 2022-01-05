@SuppressWarnings("unchecked") @Test public void testKeySanitizationWithCustomKeys() throws Exception {
  System.setProperty("dbPassword","123456");
  System.setProperty("apiKey","123456");
  EnvironmentEndpoint report=getEndpointBean();
  report.setKeysToSanitize("key");
  Map<String,Object> env=report.invoke();
  assertEquals("123456",((Map<String,Object>)env.get("systemProperties")).get("dbPassword"));
  assertEquals("******",((Map<String,Object>)env.get("systemProperties")).get("apiKey"));
}
