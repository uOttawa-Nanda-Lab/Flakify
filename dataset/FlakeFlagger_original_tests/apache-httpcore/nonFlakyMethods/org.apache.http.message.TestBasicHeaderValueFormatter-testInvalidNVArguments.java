@Test public void testInvalidNVArguments() throws Exception {
  try {
    BasicHeaderValueFormatter.formatNameValuePair((NameValuePair)null,true,null);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    BasicHeaderValueFormatter.formatParameters((NameValuePair[])null,true,BasicHeaderValueFormatter.DEFAULT);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
