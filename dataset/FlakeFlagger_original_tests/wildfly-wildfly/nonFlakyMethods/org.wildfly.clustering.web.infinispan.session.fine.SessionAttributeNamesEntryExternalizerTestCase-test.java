@Test public void test() throws ClassNotFoundException, IOException {
  ConcurrentMap<String,Integer> attributes=new ConcurrentHashMap<>();
  attributes.put("a",1);
  attributes.put("b",2);
  attributes.put("c",3);
  SessionAttributeNamesEntry entry=new SessionAttributeNamesEntry(new AtomicInteger(10),attributes);
  new ExternalizerTester<>(new SessionAttributeNamesEntryExternalizer(),SessionAttributeNamesEntryExternalizerTestCase::assertEquals).test(entry);
}
