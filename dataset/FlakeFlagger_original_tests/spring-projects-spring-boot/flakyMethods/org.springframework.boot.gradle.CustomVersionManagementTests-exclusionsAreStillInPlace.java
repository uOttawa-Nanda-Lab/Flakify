@Test public void exclusionsAreStillInPlace(){
  project.newBuild().forTasks("checkExclusions").withArguments("-PbootVersion=" + BOOT_VERSION).run();
}
