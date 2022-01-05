@Test public void classifierInBootExtension() throws Exception {
  this.project=new ProjectCreator().createProject("classifier-extension");
  this.project.newBuild().forTasks("build").withArguments("-PbootVersion=" + BOOT_VERSION,"--stacktrace","--info").run();
}
