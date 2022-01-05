@Test public void runWithoutArguments() throws Exception {
  this.thrown.expect(NoArgumentsException.class);
  this.commandRunner.run();
}
