@Test public void testCommandLineOverrides() throws Exception {
  SampleSimpleApplication.main(new String[]{"--name=Gordon"});
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("Hello Gordon"));
}
