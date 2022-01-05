@Test public void testArgsEnhanced() throws Exception {
  System.setProperty("loader.args","foo");
  PropertiesLauncher launcher=new PropertiesLauncher();
  assertEquals("[foo, bar]",Arrays.asList(launcher.getArgs("bar")).toString());
}
