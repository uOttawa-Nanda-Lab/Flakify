@Test public void configClassContext() throws Exception {
  SpringApplication.main(getArgs(getClass().getName()));
  assertTrue(getOutput().contains(SPRING_STARTUP));
}
