@Test public void testDefaultHome(){
  PropertiesLauncher launcher=new PropertiesLauncher();
  assertEquals(new File(System.getProperty("loader.home")),launcher.getHomeDirectory());
}
