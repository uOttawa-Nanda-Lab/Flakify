@Test public void testTransactionRollback() throws Exception {
  SampleBitronixApplication.main(new String[]{});
  String output=this.outputCapture.toString();
  assertThat(output,containsString(1,"---->"));
  assertThat(output,containsString(1,"----> josh"));
  assertThat(output,containsString(2,"Count is 1"));
  assertThat(output,containsString(1,"Simulated error"));
}
