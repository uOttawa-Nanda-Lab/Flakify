@Test public void singleJavaAgentWithOptions() throws MalformedURLException, IOException {
  InputArgumentsJavaAgentDetector detector=new InputArgumentsJavaAgentDetector(Arrays.asList("-javaagent:my-agent.jar=a=alpha,b=bravo"));
  assertTrue(detector.isJavaAgentJar(new File("my-agent.jar").getCanonicalFile().toURI().toURL()));
}
