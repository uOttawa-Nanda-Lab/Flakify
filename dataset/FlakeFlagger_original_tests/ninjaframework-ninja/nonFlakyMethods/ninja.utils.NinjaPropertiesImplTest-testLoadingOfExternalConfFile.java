@Test public void testLoadingOfExternalConfFile(){
  System.setProperty(NinjaProperties.NINJA_EXTERNAL_CONF,"conf/heroku.conf");
  NinjaProperties ninjaProperties=new NinjaPropertiesImpl(NinjaMode.dev);
  assertEquals("secretForHeroku",ninjaProperties.get(NinjaConstant.applicationSecret));
  assertEquals("some special parameter",ninjaProperties.get("heroku.special.property"));
  assertEquals("http://myapp.herokuapp.com:80",ninjaProperties.get("fullServerName"));
}
