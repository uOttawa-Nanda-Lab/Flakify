@Test public void templateSample() throws Exception {
  String output=this.cli.run("template.groovy");
  assertTrue("Wrong output: " + output,output.contains("Hello World!"));
}
