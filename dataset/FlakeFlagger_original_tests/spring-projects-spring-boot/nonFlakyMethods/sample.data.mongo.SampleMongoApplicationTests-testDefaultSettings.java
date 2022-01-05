@Test public void testDefaultSettings() throws Exception {
  try {
    SampleMongoApplication.main(new String[0]);
  }
 catch (  IllegalStateException ex) {
    if (serverNotRunning(ex)) {
      return;
    }
  }
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("firstName='Alice', lastName='Smith'"));
}
