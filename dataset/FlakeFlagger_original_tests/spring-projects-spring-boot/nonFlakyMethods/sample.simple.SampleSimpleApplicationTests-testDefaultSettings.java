@Test public void testDefaultSettings() throws Exception {
  SampleSimpleApplication.main(new String[0]);
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("Hello Phil"));
}
