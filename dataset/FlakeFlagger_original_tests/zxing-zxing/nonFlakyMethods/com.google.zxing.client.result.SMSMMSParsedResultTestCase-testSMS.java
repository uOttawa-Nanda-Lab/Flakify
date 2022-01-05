@Test public void testSMS(){
  doTest("sms:+15551212","+15551212",null,null,null);
  doTest("sms:+15551212?subject=foo&body=bar","+15551212","foo","bar",null);
  doTest("sms:+15551212;via=999333","+15551212",null,null,"999333");
}
