@Test public void repackageWithCommonFileDependency() throws Exception {
  ProjectConnection project=new ProjectCreator().createProject("multi-project-common-file-dependency");
  project.newBuild().forTasks("clean","build").withArguments("-PbootVersion=" + BOOT_VERSION).run();
  File buildLibs=new File("target/multi-project-common-file-dependency/build/libs");
  JarFile jarFile=new JarFile(new File(buildLibs,"multi-project-common-file-dependency.jar"));
  assertThat(jarFile.getEntry("lib/foo.jar"),notNullValue());
  jarFile.close();
}
