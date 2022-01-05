@Test public void testMMS(){
  doTest("mms:+15551212","+15551212",null,null,null);
  doTest("mms:+15551212?subject=foo&body=bar","+15551212","foo","bar",null);
  doTest("mms:+15551212;via=999333","+15551212",null,null,"999333");
}
