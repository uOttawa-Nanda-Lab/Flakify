@Test public void testUserSpecifiedJarPathWithDot() throws Exception {
  System.setProperty("loader.path","./jars/app.jar");
  System.setProperty("loader.main","demo.Application");
  PropertiesLauncher launcher=new PropertiesLauncher();
  assertEquals("[jars/app.jar]",ReflectionTestUtils.getField(launcher,"paths").toString());
  launcher.launch(new String[0]);
  waitFor("Hello World");
}
