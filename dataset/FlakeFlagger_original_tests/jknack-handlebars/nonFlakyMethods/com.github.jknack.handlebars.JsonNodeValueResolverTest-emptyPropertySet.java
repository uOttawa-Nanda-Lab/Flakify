@Test public void emptyPropertySet() throws IOException {
  Set<Entry<String,Object>> propertySet=JsonNodeValueResolver.INSTANCE.propertySet(new Object());
  assertNotNull(propertySet);
  assertEquals(0,propertySet.size());
}
