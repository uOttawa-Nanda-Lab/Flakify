@Test public void nojar() throws Exception {
  this.project=new ProjectCreator().createProject("nojar");
  this.project.newBuild().forTasks("build").withArguments("-PbootVersion=" + BOOT_VERSION,"--stacktrace").run();
  assertFalse(new File("target/nojar/build/libs").exists());
}
