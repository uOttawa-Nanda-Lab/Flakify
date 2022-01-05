@Test public void testTokenChar(){
  Header[] headers=new Header[]{new BasicHeader("Name","token0")};
  HeaderIterator hit=new BasicHeaderIterator(headers,null);
  BasicTokenIterator bti=new BasicTokenIterator(hit);
  Assert.assertTrue("letter",bti.isTokenChar('j'));
  Assert.assertFalse("control",bti.isTokenChar('\b'));
  Assert.assertFalse("separator",bti.isTokenChar('?'));
  Assert.assertTrue("other",bti.isTokenChar('-'));
}
