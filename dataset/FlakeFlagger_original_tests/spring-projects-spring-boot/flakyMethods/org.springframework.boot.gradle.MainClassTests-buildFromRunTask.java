@Test public void buildFromRunTask(){
  project.newBuild().forTasks("build").withArguments("-PbootVersion=" + BOOT_VERSION,"--info").run();
}
