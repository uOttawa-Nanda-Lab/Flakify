@Test public void flatdir() throws Exception {
  this.project=new ProjectCreator().createProject("flatdir");
  if (!this.libs.exists()) {
    this.libs.mkdirs();
  }
  FileCopyUtils.copy(new File("src/test/resources/foo.jar"),new File(this.libs,"foo-1.0.0.jar"));
  this.project.newBuild().forTasks("build").withArguments("-PbootVersion=" + BOOT_VERSION,"--stacktrace").run();
}
