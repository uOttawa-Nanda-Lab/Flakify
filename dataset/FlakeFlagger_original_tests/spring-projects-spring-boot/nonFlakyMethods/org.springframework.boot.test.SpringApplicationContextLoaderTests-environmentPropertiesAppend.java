@Test public void environmentPropertiesAppend() throws Exception {
  Map<String,Object> config=getEnvironmentProperties(AppendConfig.class);
  assertKey(config,"key","myValue");
  assertKey(config,"otherKey","otherValue");
}
