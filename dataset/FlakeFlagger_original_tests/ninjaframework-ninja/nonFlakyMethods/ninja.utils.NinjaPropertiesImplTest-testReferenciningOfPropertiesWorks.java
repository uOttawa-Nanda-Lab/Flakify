@Test public void testReferenciningOfPropertiesWorks(){
  NinjaProperties ninjaProperties=new NinjaPropertiesImpl(NinjaMode.dev);
  assertEquals("http://myserver.com:80",ninjaProperties.get("fullServerName"));
}
