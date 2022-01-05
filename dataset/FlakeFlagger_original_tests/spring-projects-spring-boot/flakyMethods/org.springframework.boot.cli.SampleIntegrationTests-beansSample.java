@Test public void beansSample() throws Exception {
  this.cli.run("beans.groovy");
  String output=this.cli.getHttpOutput();
  assertTrue("Wrong output: " + output,output.contains("Hello World!"));
}
