@Test public void testGetWithDefault(){
  NinjaProperties ninjaProperties=new NinjaPropertiesImpl(NinjaMode.dev);
  assertEquals("default",ninjaProperties.getWithDefault("non_existsing_property_to_check_defaults","default"));
  assertEquals("dev_testproperty",ninjaProperties.getWithDefault("testproperty","default"));
}
