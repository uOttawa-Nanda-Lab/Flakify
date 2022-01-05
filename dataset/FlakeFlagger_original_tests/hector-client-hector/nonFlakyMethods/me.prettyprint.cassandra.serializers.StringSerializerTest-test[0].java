@Test public void test(){
  StringSerializer e=new StringSerializer();
  Assert.assertEquals(str,e.fromByteBuffer(e.toByteBuffer(str)));
}
