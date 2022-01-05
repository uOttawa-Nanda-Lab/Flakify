@Test public void httpSample() throws Exception {
  String output=this.cli.run("http.groovy");
  assertTrue("Wrong output: " + output,output.contains("Hello World"));
}
