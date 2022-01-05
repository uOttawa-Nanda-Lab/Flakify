@Test public void testUserSpecifiedConfigName() throws Exception {
  System.setProperty("loader.config.name","foo");
  PropertiesLauncher launcher=new PropertiesLauncher();
  assertEquals("my.Application",launcher.getMainClass());
  assertEquals("[etc/]",ReflectionTestUtils.getField(launcher,"paths").toString());
}
