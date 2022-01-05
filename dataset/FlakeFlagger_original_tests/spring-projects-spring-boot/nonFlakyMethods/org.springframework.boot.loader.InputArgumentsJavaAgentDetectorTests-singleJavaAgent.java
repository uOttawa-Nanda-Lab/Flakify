@Test public void singleJavaAgent() throws MalformedURLException, IOException {
  InputArgumentsJavaAgentDetector detector=new InputArgumentsJavaAgentDetector(Arrays.asList("-javaagent:my-agent.jar"));
  assertTrue(detector.isJavaAgentJar(new File("my-agent.jar").getCanonicalFile().toURI().toURL()));
}
