@Test public void testFringeCases(){
  Header[] headers=new Header[]{new BasicHeader("Name",null),new BasicHeader("Name","    "),new BasicHeader("Name",",,,")};
  HeaderElementIterator hei=new BasicHeaderElementIterator(new BasicHeaderIterator(headers,"Name"));
  Assert.assertFalse(hei.hasNext());
  try {
    hei.next();
    Assert.fail("NoSuchElementException should have been thrown");
  }
 catch (  NoSuchElementException ex) {
  }
  Assert.assertFalse(hei.hasNext());
  try {
    hei.next();
    Assert.fail("NoSuchElementException should have been thrown");
  }
 catch (  NoSuchElementException ex) {
  }
}
