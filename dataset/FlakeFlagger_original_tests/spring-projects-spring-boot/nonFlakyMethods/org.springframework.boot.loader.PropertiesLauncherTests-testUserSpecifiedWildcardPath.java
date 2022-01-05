@Test public void testUserSpecifiedWildcardPath() throws Exception {
  System.setProperty("loader.path","jars/*");
  System.setProperty("loader.main","demo.Application");
  PropertiesLauncher launcher=new PropertiesLauncher();
  assertEquals("[jars/]",ReflectionTestUtils.getField(launcher,"paths").toString());
  launcher.launch(new String[0]);
  waitFor("Hello World");
}
