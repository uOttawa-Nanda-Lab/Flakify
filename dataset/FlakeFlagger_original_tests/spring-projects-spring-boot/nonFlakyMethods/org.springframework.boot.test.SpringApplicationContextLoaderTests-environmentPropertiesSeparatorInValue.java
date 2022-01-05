@Test public void environmentPropertiesSeparatorInValue() throws Exception {
  Map<String,Object> config=getEnvironmentProperties(SameSeparatorInValue.class);
  assertKey(config,"key","my=Value");
  assertKey(config,"anotherKey","another:Value");
}
