@Test public void testDefaultSettings() throws Exception {
  try {
    SampleSolrApplication.main(new String[0]);
  }
 catch (  IllegalStateException ex) {
    if (serverNotRunning(ex)) {
      return;
    }
  }
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("name=Sony Playstation"));
}
