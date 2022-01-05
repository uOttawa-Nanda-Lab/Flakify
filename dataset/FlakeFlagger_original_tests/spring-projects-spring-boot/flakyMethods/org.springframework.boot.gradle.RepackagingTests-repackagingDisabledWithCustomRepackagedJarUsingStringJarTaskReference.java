@Test public void repackagingDisabledWithCustomRepackagedJarUsingStringJarTaskReference(){
  project.newBuild().forTasks("clean","build","customRepackagedJarWithStringReference").withArguments("-PbootVersion=" + BOOT_VERSION,"-Prepackage=false").run();
  File buildLibs=new File("target/repackage/build/libs");
  assertTrue(new File(buildLibs,"repackage.jar").exists());
  assertFalse(new File(buildLibs,"repackage.jar.original").exists());
  assertFalse(new File(buildLibs,"repackage-sources.jar.original").exists());
  assertTrue(new File(buildLibs,"custom.jar").exists());
  assertTrue(new File(buildLibs,"custom.jar.original").exists());
}
