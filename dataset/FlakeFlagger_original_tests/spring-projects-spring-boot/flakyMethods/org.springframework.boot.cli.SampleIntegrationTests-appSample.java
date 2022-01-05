@Test public void appSample() throws Exception {
  String output=this.cli.run("app.groovy");
  URI scriptUri=new File("samples/app.groovy").toURI();
  assertTrue("Wrong output: " + output,output.contains("Hello World! From " + scriptUri));
}
