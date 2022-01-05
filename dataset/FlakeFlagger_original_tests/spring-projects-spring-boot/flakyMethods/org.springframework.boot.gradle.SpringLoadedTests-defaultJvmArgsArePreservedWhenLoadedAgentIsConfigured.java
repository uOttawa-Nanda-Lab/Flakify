@Test public void defaultJvmArgsArePreservedWhenLoadedAgentIsConfigured() throws IOException {
  ProjectConnection project=new ProjectCreator().createProject("spring-loaded-jvm-args");
  project.newBuild().forTasks("bootRun").withArguments("-PbootVersion=" + BOOT_VERSION,"-PspringLoadedVersion=" + SPRING_LOADED_VERSION,"--stacktrace").run();
  List<String> output=getOutput();
  assertOutputContains("-DSOME_ARG=someValue",output);
  assertOutputContains("-Xverify:none",output);
  assertOutputMatches("-javaagent:.*springloaded-" + SPRING_LOADED_VERSION + ".jar",output);
}
