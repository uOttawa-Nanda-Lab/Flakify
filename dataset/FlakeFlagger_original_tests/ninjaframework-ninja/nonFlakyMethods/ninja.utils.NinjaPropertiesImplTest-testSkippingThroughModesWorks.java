@Test public void testSkippingThroughModesWorks(){
  NinjaPropertiesImpl ninjaPropertiesImpl=new NinjaPropertiesImpl(NinjaMode.test);
  assertEquals("test_testproperty",ninjaPropertiesImpl.get("testproperty"));
  ninjaPropertiesImpl=new NinjaPropertiesImpl(NinjaMode.dev);
  assertEquals("dev_testproperty",ninjaPropertiesImpl.get("testproperty"));
  assertEquals("secret",ninjaPropertiesImpl.get(NinjaConstant.applicationSecret));
  ninjaPropertiesImpl=new NinjaPropertiesImpl(NinjaMode.prod);
  assertEquals("testproperty_without_prefix",ninjaPropertiesImpl.get("testproperty"));
  assertEquals("secret",ninjaPropertiesImpl.get(NinjaConstant.applicationSecret));
  System.clearProperty(NinjaConstant.MODE_KEY_NAME);
}
