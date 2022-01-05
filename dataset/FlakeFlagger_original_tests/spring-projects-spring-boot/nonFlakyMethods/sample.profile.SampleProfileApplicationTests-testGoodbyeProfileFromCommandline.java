@Test public void testGoodbyeProfileFromCommandline() throws Exception {
  SampleProfileApplication.main(new String[]{"--spring.profiles.active=goodbye"});
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("Goodbye Everyone"));
}
