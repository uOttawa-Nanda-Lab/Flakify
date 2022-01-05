@Test public void testUft8Works(){
  NinjaProperties ninjaProperties=new NinjaPropertiesImpl(NinjaMode.dev);
  assertEquals("this is utf8: öäü",ninjaProperties.get("utf8Test"));
}
