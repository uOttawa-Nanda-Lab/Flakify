@Test public void testUserSpecifiedClassLoader() throws Exception {
  System.setProperty("loader.path","jars/app.jar");
  System.setProperty("loader.classLoader",URLClassLoader.class.getName());
  PropertiesLauncher launcher=new PropertiesLauncher();
  assertEquals("[jars/app.jar]",ReflectionTestUtils.getField(launcher,"paths").toString());
  launcher.launch(new String[0]);
  waitFor("Hello World");
}
