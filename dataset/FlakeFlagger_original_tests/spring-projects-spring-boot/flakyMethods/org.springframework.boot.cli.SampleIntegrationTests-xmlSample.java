@Test public void xmlSample() throws Exception {
  String output=this.cli.run("runner.xml","runner.groovy");
  assertTrue("Wrong output: " + output,output.contains("Hello World"));
}
