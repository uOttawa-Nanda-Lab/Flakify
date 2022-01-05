@Test public void createdClassLoaderContainsUrlsFromThreadContextClassLoader() throws Exception {
  final URL[] urls=new URL[]{new URL("file:one"),new URL("file:two")};
  doWithTccl(new URLClassLoader(urls),new Callable<Void>(){
    @Override public Void call() throws Exception {
      ClassLoader classLoader=ExecutableArchiveLauncherTests.this.launcher.createClassLoader(new URL[0]);
      assertClassLoaderUrls(classLoader,urls);
      return null;
    }
  }
);
}
