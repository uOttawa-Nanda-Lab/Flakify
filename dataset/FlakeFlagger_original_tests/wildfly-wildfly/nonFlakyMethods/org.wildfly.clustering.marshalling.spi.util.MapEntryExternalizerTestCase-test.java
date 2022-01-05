@Test public void test() throws ClassNotFoundException, IOException {
  Object key="key";
  Object value="value";
  new ExternalizerTester<>(DefaultExternalizer.SIMPLE_ENTRY.cast(AbstractMap.SimpleEntry.class)).test(new AbstractMap.SimpleEntry<>(key,value));
  new ExternalizerTester<>(DefaultExternalizer.SIMPLE_IMMUTABLE_ENTRY.cast(AbstractMap.SimpleImmutableEntry.class)).test(new AbstractMap.SimpleImmutableEntry<>(key,value));
}
