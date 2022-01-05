@Test public void testNothingHappensWhenApplicationSecretIsThere() throws Exception {
  PropertiesConfiguration defaultConfiguration=new PropertiesConfiguration();
  Configuration compositeConfiguration=new PropertiesConfiguration();
  compositeConfiguration.setProperty(NinjaConstant.applicationSecret,"secret");
  String uuid=UUID.randomUUID().toString();
  String baseDirWithoutTrailingSlash="/tmp/ninja-test-" + uuid;
  boolean isProd=true;
  NinjaPropertiesImplTool.checkThatApplicationSecretIsSet(isProd,baseDirWithoutTrailingSlash,defaultConfiguration,compositeConfiguration);
  assertFalse(new File(baseDirWithoutTrailingSlash + "src/main/java/conf/application.conf").exists());
  isProd=false;
  NinjaPropertiesImplTool.checkThatApplicationSecretIsSet(isProd,baseDirWithoutTrailingSlash,defaultConfiguration,compositeConfiguration);
  assertFalse(new File(baseDirWithoutTrailingSlash + "src/main/java/conf/application.conf").exists());
  FileUtils.deleteDirectory(new File(baseDirWithoutTrailingSlash));
}
