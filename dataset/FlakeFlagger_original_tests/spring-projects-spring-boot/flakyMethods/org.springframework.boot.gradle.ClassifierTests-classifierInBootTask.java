@Test public void classifierInBootTask() throws Exception {
  this.project=new ProjectCreator().createProject("classifier");
  this.project.newBuild().forTasks("build").withArguments("-PbootVersion=" + BOOT_VERSION,"--stacktrace").run();
  checkFilesExist("classifier");
}
