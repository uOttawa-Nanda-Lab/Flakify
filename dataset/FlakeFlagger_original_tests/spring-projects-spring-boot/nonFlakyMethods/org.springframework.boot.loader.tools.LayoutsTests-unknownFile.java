@Test public void unknownFile() throws Exception {
  this.thrown.equals(IllegalStateException.class);
  this.thrown.expectMessage("Unable to deduce layout for 'test.txt'");
  Layouts.forFile(new File("test.txt"));
}
