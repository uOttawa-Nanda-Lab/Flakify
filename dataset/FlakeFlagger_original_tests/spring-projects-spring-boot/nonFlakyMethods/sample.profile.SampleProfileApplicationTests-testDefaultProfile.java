@Test public void testDefaultProfile() throws Exception {
  SampleProfileApplication.main(new String[0]);
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("Hello Phil"));
}
