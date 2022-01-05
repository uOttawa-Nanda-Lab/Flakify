@Test public void cleanInstallVersionManagement() throws Exception {
  project=new ProjectCreator().createProject("installer-io");
  project.newBuild().forTasks("install").withArguments("-PbootVersion=" + BOOT_VERSION,"--stacktrace").run();
}
