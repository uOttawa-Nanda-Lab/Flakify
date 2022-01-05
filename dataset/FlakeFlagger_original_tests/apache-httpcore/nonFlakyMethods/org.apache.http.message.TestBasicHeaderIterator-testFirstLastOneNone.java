@Test public void testFirstLastOneNone(){
  Header[] headers=new Header[]{new BasicHeader("match","value0"),new BasicHeader("mismatch","value1, value1.1"),new BasicHeader("single","value2=whatever"),new BasicHeader("match","value3;tag=nil")};
  HeaderIterator hit=new BasicHeaderIterator(headers,null);
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("0",headers[0],hit.nextHeader());
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("1",headers[1],hit.nextHeader());
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("2",headers[2],hit.nextHeader());
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("3",headers[3],hit.nextHeader());
  Assert.assertFalse(hit.hasNext());
  hit=new BasicHeaderIterator(headers,"match");
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("0",headers[0],hit.nextHeader());
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("3",headers[3],hit.nextHeader());
  Assert.assertFalse(hit.hasNext());
  hit=new BasicHeaderIterator(headers,"single");
  Assert.assertTrue(hit.hasNext());
  Assert.assertEquals("2",headers[2],hit.nextHeader());
  Assert.assertFalse(hit.hasNext());
  hit=new BasicHeaderIterator(headers,"way-off");
  Assert.assertFalse(hit.hasNext());
}
