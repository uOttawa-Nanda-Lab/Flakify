@Test public void testBuildEnvProperties(){
  appender.setInitialContextFactoryName("icfn");
  appender.setProviderURL("url");
  appender.setURLPkgPrefixes("pkgPref");
  appender.setSecurityPrincipalName("user");
  appender.setSecurityCredentials("cred");
  Properties props=appender.buildEnvProperties();
  assertEquals(5,props.size());
  assertEquals(appender.getInitialContextFactoryName(),props.getProperty(Context.INITIAL_CONTEXT_FACTORY));
  assertEquals(appender.getProviderURL(),props.getProperty(Context.PROVIDER_URL));
  assertEquals(appender.getURLPkgPrefixes(),props.getProperty(Context.URL_PKG_PREFIXES));
  assertEquals(appender.getSecurityPrincipalName(),props.getProperty(Context.SECURITY_PRINCIPAL));
  assertEquals(appender.getSecurityCredentials(),props.getProperty(Context.SECURITY_CREDENTIALS));
}
