@Test public void testMultiHeader(){
  Header[] headers=new Header[]{new BasicHeader("Name","value0"),new BasicHeader("Name","value1")};
  HeaderElementIterator hei=new BasicHeaderElementIterator(new BasicHeaderIterator(headers,"Name"));
  Assert.assertTrue(hei.hasNext());
  HeaderElement elem=hei.next();
  Assert.assertEquals("The two header values must be equal","value0",elem.getName());
  Assert.assertTrue(hei.hasNext());
  elem=hei.next();
  Assert.assertEquals("The two header values must be equal","value1",elem.getName());
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
