@Test public void testGetIntegerWithDefault(){
  NinjaProperties ninjaProperties=new NinjaPropertiesImpl(NinjaMode.dev);
  assertEquals(Integer.valueOf(1),ninjaProperties.getIntegerWithDefault("non_existsing_property_to_check_defaults",1));
  assertEquals(Integer.valueOf(123456789),ninjaProperties.getIntegerWithDefault("integerTest",1));
}
