@Test public void integrationSample() throws Exception {
  String output=this.cli.run("integration.groovy");
  assertTrue("Wrong output: " + output,output.contains("Hello, World"));
}
