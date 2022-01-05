@Test public void jobWebSample() throws Exception {
  String output=this.cli.run("job.groovy","web.groovy","foo=bar");
  assertTrue("Wrong output: " + output,output.contains("completed with the following parameters"));
  String result=this.cli.getHttpOutput();
  assertEquals("World!",result);
}
