@Test public void testDefaultSettings() throws Exception {
  assertEquals(0,SpringApplication.exit(SpringApplication.run(SampleBatchApplication.class)));
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("completed with the following parameters"));
}
