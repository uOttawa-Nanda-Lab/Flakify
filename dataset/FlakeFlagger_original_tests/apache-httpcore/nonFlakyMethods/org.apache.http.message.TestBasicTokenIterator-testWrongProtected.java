@Test public void testWrongProtected(){
  Header[] headers=new Header[]{new BasicHeader("Name","token1,token2")};
  HeaderIterator hit=new BasicHeaderIterator(headers,null);
  BasicTokenIterator bti=new BasicTokenIterator(hit);
  try {
    bti.findTokenStart(-1);
    Assert.fail("tokenStart: negative index not detected");
  }
 catch (  IllegalArgumentException iax) {
  }
  try {
    bti.findTokenSeparator(-1);
    Assert.fail("tokenSeparator: negative index not detected");
  }
 catch (  IllegalArgumentException iax) {
  }
  try {
    bti.findTokenEnd(-1);
    Assert.fail("tokenEnd: negative index not detected");
  }
 catch (  IllegalArgumentException iax) {
  }
}
