@Test(expected=IllegalStateException.class) public void testNonexistentConfigLocation() throws Exception {
  this.loggingSystem.beforeInitialize();
  this.loggingSystem.initialize("classpath:log4j2-nonexistent.xml",null);
}
