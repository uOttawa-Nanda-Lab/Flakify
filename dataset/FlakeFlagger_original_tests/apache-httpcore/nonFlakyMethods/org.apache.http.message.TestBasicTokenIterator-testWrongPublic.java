@Test public void testWrongPublic(){
  try {
    new BasicTokenIterator(null);
    Assert.fail("null argument not detected");
  }
 catch (  IllegalArgumentException iax) {
  }
  Header[] headers=new Header[]{new BasicHeader("Name"," "),new BasicHeader("Name",""),new BasicHeader("Name",","),new BasicHeader("Name"," ,, ")};
  HeaderIterator hit=new BasicHeaderIterator(headers,null);
  TokenIterator ti=new BasicTokenIterator(hit);
  try {
    ti.next();
    Assert.fail("next after end not detected");
  }
 catch (  NoSuchElementException nsx) {
  }
  try {
    ti.remove();
    Assert.fail("unsupported remove not detected");
  }
 catch (  UnsupportedOperationException uox) {
  }
}
