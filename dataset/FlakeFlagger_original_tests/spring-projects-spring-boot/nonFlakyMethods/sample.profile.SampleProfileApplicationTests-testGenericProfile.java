@Test public void testGenericProfile() throws Exception {
  System.setProperty("spring.profiles.active","generic");
  SampleProfileApplication.main(new String[0]);
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("Bonjour Phil"));
}
