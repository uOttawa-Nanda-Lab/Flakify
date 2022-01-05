@Test public void runCommand() throws Exception {
  this.commandRunner.run("command","--arg1","arg2");
  verify(this.regularCommand).run("--arg1","arg2");
}
