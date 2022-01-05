@Test public void javaAgentJarsAreExcludedFromClasspath() throws Exception {
  URL javaAgent=new File("my-agent.jar").getCanonicalFile().toURI().toURL();
  final URL one=new URL("file:one");
  given(this.javaAgentDetector.isJavaAgentJar(javaAgent)).willReturn(true);
  doWithTccl(new URLClassLoader(new URL[]{javaAgent,one}),new Callable<Void>(){
    @Override public Void call() throws Exception {
      ClassLoader classLoader=ExecutableArchiveLauncherTests.this.launcher.createClassLoader(new URL[0]);
      assertClassLoaderUrls(classLoader,new URL[]{one});
      return null;
    }
  }
);
}
