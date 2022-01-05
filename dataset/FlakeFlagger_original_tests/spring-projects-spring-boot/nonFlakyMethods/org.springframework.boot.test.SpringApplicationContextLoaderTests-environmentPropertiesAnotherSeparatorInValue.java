@Test public void environmentPropertiesAnotherSeparatorInValue() throws Exception {
  Map<String,Object> config=getEnvironmentProperties(AnotherSeparatorInValue.class);
  assertKey(config,"key","my:Value");
  assertKey(config,"anotherKey","another=Value");
}
