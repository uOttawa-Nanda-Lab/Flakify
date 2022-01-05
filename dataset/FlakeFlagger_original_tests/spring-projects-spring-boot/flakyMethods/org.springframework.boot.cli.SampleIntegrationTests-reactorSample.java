@Test public void reactorSample() throws Exception {
  String output=this.cli.run("reactor.groovy","Phil");
  int count=0;
  while (!output.contains("Hello Phil") && count++ < 5) {
    Thread.sleep(200);
    output=this.cli.getOutput();
  }
  assertTrue("Wrong output: " + output,output.contains("Hello Phil"));
}
