@Test public void jobSample() throws Exception {
  String output=this.cli.run("job.groovy","foo=bar");
  assertTrue("Wrong output: " + output,output.contains("completed with the following parameters"));
}
