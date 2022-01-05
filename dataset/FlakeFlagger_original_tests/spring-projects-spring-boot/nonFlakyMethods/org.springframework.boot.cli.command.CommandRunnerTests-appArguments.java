@Test public void appArguments() throws Exception {
  this.commandRunner.runAndHandleErrors("command","--","--debug","bar");
  verify(this.regularCommand).run("--","--debug","bar");
  assertNull(System.getProperty("debug"));
}
