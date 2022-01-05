@Test(expected=IllegalArgumentException.class) public void emptyApplicationContext() throws Exception {
  SpringApplication.main(getArgs());
  assertTrue(getOutput().contains(SPRING_STARTUP));
}
