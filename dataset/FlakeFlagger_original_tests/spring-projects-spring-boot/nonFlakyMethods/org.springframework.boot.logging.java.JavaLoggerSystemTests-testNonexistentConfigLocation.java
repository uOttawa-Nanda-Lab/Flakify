@Test(expected=IllegalStateException.class) public void testNonexistentConfigLocation() throws Exception {
  this.loggingSystem.beforeInitialize();
  this.loggingSystem.initialize("classpath:logging-nonexistent.properties",null);
}
