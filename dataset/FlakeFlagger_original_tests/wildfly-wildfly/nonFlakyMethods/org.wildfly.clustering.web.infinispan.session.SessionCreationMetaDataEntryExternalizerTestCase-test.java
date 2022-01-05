@Test public void test() throws ClassNotFoundException, IOException {
  SessionCreationMetaData metaData=new SimpleSessionCreationMetaData(Instant.now());
  metaData.setMaxInactiveInterval(Duration.ofMinutes(10));
  SessionCreationMetaDataEntry<Object> entry=new SessionCreationMetaDataEntry<>(metaData);
  new ExternalizerTester<>(new SessionCreationMetaDataEntryExternalizer(),SessionCreationMetaDataEntryExternalizerTestCase::assertEquals).test(entry);
}
