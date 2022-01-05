@Test public void testSystemPropertySpecifiedMain() throws Exception {
  System.setProperty("loader.main","foo.Bar");
  PropertiesLauncher launcher=new PropertiesLauncher();
  assertEquals("foo.Bar",launcher.getMainClass());
}
