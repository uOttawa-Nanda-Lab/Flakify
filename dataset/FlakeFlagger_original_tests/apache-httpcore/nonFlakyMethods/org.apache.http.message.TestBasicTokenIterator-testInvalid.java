@Test public void testInvalid(){
  Header[] headers=new Header[]{new BasicHeader("in","token0=token1"),new BasicHeader("no","token0 token1"),new BasicHeader("pre","<token0,token1"),new BasicHeader("post","token0,token1=")};
  HeaderIterator hit=new BasicHeaderIterator(headers,"in");
  TokenIterator ti=new BasicTokenIterator(hit);
  Assert.assertTrue(ti.hasNext());
  try {
    ti.nextToken();
    Assert.fail("invalid infix character not detected");
  }
 catch (  ParseException px) {
  }
  hit=new BasicHeaderIterator(headers,"no");
  ti=new BasicTokenIterator(hit);
  Assert.assertTrue(ti.hasNext());
  try {
    ti.nextToken();
    Assert.fail("missing token separator not detected");
  }
 catch (  ParseException px) {
  }
  hit=new BasicHeaderIterator(headers,"pre");
  try {
    new BasicTokenIterator(hit);
    Assert.fail("invalid prefix character not detected");
  }
 catch (  ParseException px) {
  }
  hit=new BasicHeaderIterator(headers,"post");
  ti=new BasicTokenIterator(hit);
  Assert.assertTrue(ti.hasNext());
  Assert.assertEquals("token0","token0",ti.nextToken());
  Assert.assertTrue(ti.hasNext());
  try {
    ti.nextToken();
    Assert.fail("invalid postfix character not detected");
  }
 catch (  ParseException px) {
  }
}
