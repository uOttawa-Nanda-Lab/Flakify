@Test public void autoConfigFromSystemProperties() throws JoranException {
  doAutoConfigFromSystemProperties(ClassicTestConstants.INPUT_PREFIX + "autoConfig.xml");
  doAutoConfigFromSystemProperties("autoConfigAsResource.xml");
  doAutoConfigFromSystemProperties("file:" + ClassicTestConstants.INPUT_PREFIX + "autoConfig.xml");
}
