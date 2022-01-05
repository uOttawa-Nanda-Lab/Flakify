@Test public void springLoadedCanBeUsedWithGradle16() throws IOException {
  ProjectConnection project=new ProjectCreator("1.6").createProject("spring-loaded-old-gradle");
  project.newBuild().forTasks("bootRun").withArguments("-PbootVersion=" + BOOT_VERSION,"-PspringLoadedVersion=" + SPRING_LOADED_VERSION,"--stacktrace").run();
  List<String> output=getOutput();
  assertOutputMatches("-javaagent:.*springloaded-" + SPRING_LOADED_VERSION + ".jar",output);
}
