@Test public void test() throws ClassNotFoundException, IOException {
  new ExternalizerTester<>(new AuthenticationEntryExternalizer<>(),AuthenticationEntryExternalizerTestCase::assertEquals).test(new AuthenticationEntry<>("username"));
}
