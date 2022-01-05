@Test public void testDefaultSettings() throws Exception {
  SampleSpringXmlApplication.main(new String[0]);
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("Hello World"));
}
