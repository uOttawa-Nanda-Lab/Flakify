@Test public void environmentPropertiesSimple() throws Exception {
  Map<String,Object> config=getEnvironmentProperties(SimpleConfig.class);
  assertKey(config,"key","myValue");
  assertKey(config,"anotherKey","anotherValue");
}
