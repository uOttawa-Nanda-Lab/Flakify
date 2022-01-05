@Test public void testUserSpecifiedMain() throws Exception {
  PropertiesLauncher launcher=new PropertiesLauncher();
  assertEquals("demo.Application",launcher.getMainClass());
  assertNull(System.getProperty("loader.main"));
}
