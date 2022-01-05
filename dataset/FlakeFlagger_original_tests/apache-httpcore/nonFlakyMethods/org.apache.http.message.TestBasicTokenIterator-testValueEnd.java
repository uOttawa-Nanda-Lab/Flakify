@Test public void testValueEnd(){
  Header[] headers=new Header[]{new BasicHeader("Name","token0"),new BasicHeader("Name","token1 "),new BasicHeader("Name","token2,"),new BasicHeader("Name","token3 ,"),new BasicHeader("Name","token4, "),new BasicHeader("Name","token5 , ")};
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
  Assert.assertTrue(ti.hasNext());
  Assert.assertEquals("token4","token4",ti.nextToken());
  Assert.assertTrue(ti.hasNext());
  Assert.assertEquals("token5","token5",ti.nextToken());
  Assert.assertFalse(ti.hasNext());
}
