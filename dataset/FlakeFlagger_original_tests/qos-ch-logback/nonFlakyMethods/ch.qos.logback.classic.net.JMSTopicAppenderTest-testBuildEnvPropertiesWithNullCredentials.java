@Test public void testBuildEnvPropertiesWithNullCredentials(){
  appender.setInitialContextFactoryName("icfn");
  appender.setProviderURL("url");
  appender.setURLPkgPrefixes("pkgPref");
  appender.setSecurityPrincipalName("user");
  appender.setSecurityCredentials(null);
  Properties props=appender.buildEnvProperties();
  assertEquals(4,props.size());
  assertEquals(appender.getInitialContextFactoryName(),props.getProperty(Context.INITIAL_CONTEXT_FACTORY));
  assertEquals(appender.getProviderURL(),props.getProperty(Context.PROVIDER_URL));
  assertEquals(appender.getURLPkgPrefixes(),props.getProperty(Context.URL_PKG_PREFIXES));
  assertEquals(appender.getSecurityPrincipalName(),props.getProperty(Context.SECURITY_PRINCIPAL));
  assertEquals(null,props.getProperty(Context.SECURITY_CREDENTIALS));
  assertEquals(1,context.getStatusManager().getCount());
}
