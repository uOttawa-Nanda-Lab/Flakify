@Test public void testCustomClassLoaderCreation() throws Exception {
  System.setProperty("loader.classLoader",TestLoader.class.getName());
  PropertiesLauncher launcher=new PropertiesLauncher();
  ClassLoader loader=launcher.createClassLoader(Collections.<Archive>emptyList());
  assertNotNull(loader);
  assertEquals(TestLoader.class.getName(),loader.getClass().getName());
}
