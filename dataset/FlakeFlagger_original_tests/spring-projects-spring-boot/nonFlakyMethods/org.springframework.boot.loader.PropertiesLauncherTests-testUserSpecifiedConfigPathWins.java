@Test public void testUserSpecifiedConfigPathWins() throws Exception {
  System.setProperty("loader.config.name","foo");
  System.setProperty("loader.config.location","classpath:bar.properties");
  PropertiesLauncher launcher=new PropertiesLauncher();
  assertEquals("my.BarApplication",launcher.getMainClass());
}
