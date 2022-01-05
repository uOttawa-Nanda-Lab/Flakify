@Test public void testInvalid(){
  HeaderIterator hit=null;
  try {
    hit=new BasicHeaderIterator(null,"whatever");
    Assert.fail("null headers not detected");
  }
 catch (  IllegalArgumentException iax) {
  }
  hit=new BasicHeaderIterator(new Header[0],"whatever");
  Assert.assertFalse(hit.hasNext());
  try {
    hit.nextHeader();
    Assert.fail("next beyond end not detected");
  }
 catch (  NoSuchElementException nsx) {
  }
}
