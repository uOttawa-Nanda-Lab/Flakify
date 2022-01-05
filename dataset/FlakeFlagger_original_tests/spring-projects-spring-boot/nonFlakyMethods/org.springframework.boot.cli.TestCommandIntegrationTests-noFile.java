@Test public void noFile() throws Exception {
  TestCommand command=new TestCommand();
  this.thrown.expect(RuntimeException.class);
  this.thrown.expectMessage("Can't find nothing.groovy");
  command.run("nothing.groovy");
}
