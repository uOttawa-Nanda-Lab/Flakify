@Test(expected=IllegalStateException.class) public void testNonexistentConfigLocation() throws Exception {
  this.loggingSystem.beforeInitialize();
  this.loggingSystem.initialize("classpath:logback-nonexistent.xml",null);
}
