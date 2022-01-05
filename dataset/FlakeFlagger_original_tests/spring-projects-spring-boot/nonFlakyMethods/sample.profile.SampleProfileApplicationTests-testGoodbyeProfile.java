@Test public void testGoodbyeProfile() throws Exception {
  System.setProperty("spring.profiles.active","goodbye");
  SampleProfileApplication.main(new String[0]);
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("Goodbye Everyone"));
}
