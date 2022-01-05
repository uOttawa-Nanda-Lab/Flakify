@Test public void repackagingEnabled(){
  project.newBuild().forTasks("clean","build").withArguments("-PbootVersion=" + BOOT_VERSION,"-Prepackage=true").run();
  File buildLibs=new File("target/repackage/build/libs");
  assertTrue(new File(buildLibs,"repackage.jar").exists());
  assertTrue(new File(buildLibs,"repackage.jar.original").exists());
  assertFalse(new File(buildLibs,"repackage-sources.jar.original").exists());
}
