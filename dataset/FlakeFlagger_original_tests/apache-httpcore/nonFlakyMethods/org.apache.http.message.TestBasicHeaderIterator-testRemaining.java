@Test public void testRemaining(){
  Header[] headers=new Header[]{new BasicHeader("Name","value0"),new BasicHeader("nAme","value1, value1.1"),new BasicHeader("naMe","value2=whatever"),new BasicHeader("namE","value3;tag=nil")};
  HeaderIterator hit=new BasicHeaderIterator(headers,null);
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("0",headers[0],hit.next());
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("1",headers[1],hit.next());
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("2",headers[2],hit.next());
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("3",headers[3],hit.next());
  Assert.assertFalse(hit.hasNext());
  hit=new BasicHeaderIterator(headers,null);
  Assert.assertTrue(hit.hasNext());
  try {
    hit.remove();
    Assert.fail("remove not detected");
  }
 catch (  UnsupportedOperationException uox) {
  }
  Assert.assertTrue("no next",((BasicHeaderIterator)hit).findNext(-3) < 0);
}
