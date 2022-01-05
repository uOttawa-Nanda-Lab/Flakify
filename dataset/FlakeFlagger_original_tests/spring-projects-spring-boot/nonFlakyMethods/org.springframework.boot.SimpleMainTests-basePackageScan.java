@Test public void basePackageScan() throws Exception {
  SpringApplication.main(getArgs(ClassUtils.getPackageName(getClass()) + ".sampleconfig"));
  assertTrue(getOutput().contains(SPRING_STARTUP));
}
