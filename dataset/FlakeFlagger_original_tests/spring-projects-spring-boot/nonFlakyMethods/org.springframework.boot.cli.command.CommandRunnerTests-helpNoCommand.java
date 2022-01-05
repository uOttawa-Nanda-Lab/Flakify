@Test public void helpNoCommand() throws Exception {
  this.thrown.expect(NoHelpCommandArgumentsException.class);
  this.commandRunner.run("help");
}
