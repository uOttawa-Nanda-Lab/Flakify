@Test public void help() throws Exception {
  this.commandRunner.run("help","command");
  verify(this.regularCommand).getHelp();
}
