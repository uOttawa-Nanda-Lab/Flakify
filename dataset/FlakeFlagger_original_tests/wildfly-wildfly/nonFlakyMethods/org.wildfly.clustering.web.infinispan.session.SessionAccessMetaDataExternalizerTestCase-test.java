@Test public void test() throws ClassNotFoundException, IOException {
  SimpleSessionAccessMetaData metaData=new SimpleSessionAccessMetaData();
  metaData.setLastAccessedDuration(Duration.ofMinutes(1));
  new ExternalizerTester<>(new SessionAccessMetaDataExternalizer(),SessionAccessMetaDataExternalizerTestCase::assertEquals).test(metaData);
}
