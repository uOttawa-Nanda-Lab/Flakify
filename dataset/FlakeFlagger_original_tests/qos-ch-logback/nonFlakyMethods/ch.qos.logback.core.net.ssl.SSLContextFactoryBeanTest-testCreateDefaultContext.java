@Test public void testCreateDefaultContext() throws Exception {
  assertNotNull(factoryBean.createContext(context));
  assertTrue(context.hasInfoMatching(SSL_CONFIGURATION_MESSAGE_PATTERN));
}
