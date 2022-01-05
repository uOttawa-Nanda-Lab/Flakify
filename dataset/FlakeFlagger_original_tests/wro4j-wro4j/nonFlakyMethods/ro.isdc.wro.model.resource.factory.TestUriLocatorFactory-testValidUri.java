@Test public void testValidUri() throws Exception {
  factory.addLocator(new ClasspathUriLocator());
  Assert.assertNotNull(factory.locate("classpath:" + WroUtil.toPackageAsFolder(TestUriLocatorFactory.class)));
}
