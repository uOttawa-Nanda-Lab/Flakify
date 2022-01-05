@Test public void helpUnknownCommand() throws Exception {
  this.thrown.expect(NoSuchCommandException.class);
  this.commandRunner.run("help","missing");
}
