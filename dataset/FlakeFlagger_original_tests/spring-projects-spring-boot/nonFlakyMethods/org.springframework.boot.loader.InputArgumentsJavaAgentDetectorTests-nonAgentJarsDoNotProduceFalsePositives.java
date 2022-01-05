@Test public void nonAgentJarsDoNotProduceFalsePositives() throws MalformedURLException, IOException {
  InputArgumentsJavaAgentDetector detector=new InputArgumentsJavaAgentDetector(Arrays.asList("-javaagent:my-agent.jar"));
  assertFalse(detector.isJavaAgentJar(new File("something-else.jar").getCanonicalFile().toURI().toURL()));
}
