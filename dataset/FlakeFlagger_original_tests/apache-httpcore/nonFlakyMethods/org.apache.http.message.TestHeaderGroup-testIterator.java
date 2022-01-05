@Test public void testIterator(){
  HeaderGroup headergroup=new HeaderGroup();
  Iterator<Header> i=headergroup.iterator();
  Assert.assertNotNull(i);
  Assert.assertFalse(i.hasNext());
}
