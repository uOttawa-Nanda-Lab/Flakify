@Test public void repackageWithTransitiveFileDependency() throws Exception {
  ProjectConnection project=new ProjectCreator().createProject("multi-project-transitive-file-dependency");
  project.newBuild().forTasks("clean","build").withArguments("-PbootVersion=" + BOOT_VERSION).run();
  File buildLibs=new File("target/multi-project-transitive-file-dependency/main/build/libs");
  JarFile jarFile=new JarFile(new File(buildLibs,"main.jar"));
  assertThat(jarFile.getEntry("lib/commons-logging-1.1.3.jar"),notNullValue());
  assertThat(jarFile.getEntry("lib/foo.jar"),notNullValue());
  jarFile.close();
}
