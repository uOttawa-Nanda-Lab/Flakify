@Test public void uiSample() throws Exception {
  this.cli.run("ui.groovy","--classpath=.:src/test/resources");
  String result=this.cli.getHttpOutput();
  assertTrue("Wrong output: " + result,result.contains("Hello World"));
  result=this.cli.getHttpOutput("/css/bootstrap.min.css");
  assertTrue("Wrong output: " + result,result.contains("container"));
}
