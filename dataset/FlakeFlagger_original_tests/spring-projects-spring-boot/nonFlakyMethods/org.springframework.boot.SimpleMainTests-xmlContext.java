@Test public void xmlContext() throws Exception {
  SpringApplication.main(getArgs("org/springframework/boot/sample-beans.xml"));
  assertTrue(getOutput().contains(SPRING_STARTUP));
}
