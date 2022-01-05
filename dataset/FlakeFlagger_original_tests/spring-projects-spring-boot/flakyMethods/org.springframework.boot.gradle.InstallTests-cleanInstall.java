@Test public void cleanInstall() throws Exception {
  project=new ProjectCreator().createProject("installer");
  project.newBuild().forTasks("install").withArguments("-PbootVersion=" + BOOT_VERSION,"--stacktrace").run();
}
