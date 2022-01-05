@Test public void txSample() throws Exception {
  String output=this.cli.run("tx.groovy");
  assertTrue("Wrong output: " + output,output.contains("Foo count="));
}
