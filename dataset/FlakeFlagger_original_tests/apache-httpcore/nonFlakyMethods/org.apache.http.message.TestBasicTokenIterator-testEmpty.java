@Test public void testEmpty(){
  Header[] headers=new Header[]{new BasicHeader("Name"," "),new BasicHeader("Name",""),new BasicHeader("Name",","),new BasicHeader("Name"," ,, ")};
  HeaderIterator hit=new BasicHeaderIterator(headers,null);
  TokenIterator ti=new BasicTokenIterator(hit);
  Assert.assertFalse(ti.hasNext());
  hit=new BasicHeaderIterator(headers,"empty");
  ti=new BasicTokenIterator(hit);
  Assert.assertFalse(ti.hasNext());
}
