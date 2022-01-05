@Test public void testCollision(){
  HeaderMap headerMap=new HeaderMap();
  headerMap.put(new HttpString("Link"),"a");
  headerMap.put(new HttpString("Rest"),"b");
  Assert.assertEquals("a",headerMap.getFirst(new HttpString("Link")));
  Assert.assertEquals("b",headerMap.getFirst(new HttpString("Rest")));
  Assert.assertEquals("a",headerMap.getFirst("Link"));
  Assert.assertEquals("b",headerMap.getFirst("Rest"));
}
