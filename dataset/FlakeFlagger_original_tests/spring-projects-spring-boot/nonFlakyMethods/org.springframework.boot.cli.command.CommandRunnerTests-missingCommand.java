@Test public void missingCommand() throws Exception {
  this.thrown.expect(NoSuchCommandException.class);
  this.commandRunner.run("missing");
}
