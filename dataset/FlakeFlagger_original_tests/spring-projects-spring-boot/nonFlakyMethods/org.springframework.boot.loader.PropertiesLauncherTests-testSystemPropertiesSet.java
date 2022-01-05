@Test public void testSystemPropertiesSet() throws Exception {
  System.setProperty("loader.system","true");
  new PropertiesLauncher();
  assertEquals("demo.Application",System.getProperty("loader.main"));
}
