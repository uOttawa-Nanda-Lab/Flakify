@Test public void customSpringVersionIsUsed(){
  project.newBuild().forTasks("checkSpringVersion").withArguments("-PbootVersion=" + BOOT_VERSION).run();
}
