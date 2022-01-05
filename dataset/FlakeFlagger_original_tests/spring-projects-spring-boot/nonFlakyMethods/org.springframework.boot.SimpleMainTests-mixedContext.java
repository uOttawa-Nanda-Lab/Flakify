@Test public void mixedContext() throws Exception {
  SpringApplication.main(getArgs(getClass().getName(),"org/springframework/boot/sample-beans.xml"));
  assertTrue(getOutput().contains(SPRING_STARTUP));
}
