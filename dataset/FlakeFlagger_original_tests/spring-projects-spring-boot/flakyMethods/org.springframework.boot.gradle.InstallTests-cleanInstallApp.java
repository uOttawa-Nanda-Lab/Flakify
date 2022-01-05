@Test public void cleanInstallApp() throws Exception {
  project=new ProjectCreator().createProject("install-app");
  project.newBuild().forTasks("installApp").withArguments("-PbootVersion=" + BOOT_VERSION,"--stacktrace","--info").run();
}
