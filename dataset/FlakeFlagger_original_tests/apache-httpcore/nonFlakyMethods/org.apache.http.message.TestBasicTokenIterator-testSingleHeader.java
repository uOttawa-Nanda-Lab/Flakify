@Test public void testSingleHeader(){
  Header[] headers=new Header[]{new BasicHeader("Name","token0,token1, token2 , token3")};
  HeaderIterator hit=new BasicHeaderIterator(headers,null);
  TokenIterator ti=new BasicTokenIterator(hit);
  Assert.assertTrue(ti.hasNext());
  Assert.assertEquals("token0","token0",ti.nextToken());
  Assert.assertTrue(ti.hasNext());
  Assert.assertEquals("token1","token1",ti.nextToken());
  Assert.assertTrue(ti.hasNext());
  Assert.assertEquals("token2","token2",ti.nextToken());
  Assert.assertTrue(ti.hasNext());
  Assert.assertEquals("token3","token3",ti.nextToken());
  Assert.assertFalse(ti.hasNext());
  headers=new Header[]{new BasicHeader("Name","token0")};
  hit=new BasicHeaderIterator(headers,null);
  ti=new BasicTokenIterator(hit);
  Assert.assertTrue(ti.hasNext());
  Assert.assertEquals("token0","token0",ti.nextToken());
  Assert.assertFalse(ti.hasNext());
}
