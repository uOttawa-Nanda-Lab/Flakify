@Test public void testLoadingOfExternalConfigurationFileWorksAndPrefixedConfigurationsSetRead(){
  System.setProperty(NinjaProperties.NINJA_EXTERNAL_CONF,"conf/heroku.conf");
  NinjaProperties ninjaProperties=new NinjaPropertiesImpl(NinjaMode.test);
  assertEquals("http://myapp-test.herokuapp.com:80",ninjaProperties.get("fullServerName"));
}
