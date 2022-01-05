@Test public void multipleJavaAgents() throws MalformedURLException, IOException {
  InputArgumentsJavaAgentDetector detector=new InputArgumentsJavaAgentDetector(Arrays.asList("-javaagent:my-agent.jar","-javaagent:my-other-agent.jar"));
  assertTrue(detector.isJavaAgentJar(new File("my-agent.jar").getCanonicalFile().toURI().toURL()));
  assertTrue(detector.isJavaAgentJar(new File("my-other-agent.jar").getCanonicalFile().toURI().toURL()));
}
