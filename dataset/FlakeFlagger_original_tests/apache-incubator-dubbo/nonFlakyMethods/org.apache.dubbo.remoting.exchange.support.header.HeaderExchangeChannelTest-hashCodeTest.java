@Test public void hashCodeTest(){
  final int prime=31;
  int result=1;
  result=prime * result + ((channel == null) ? 0 : channel.hashCode());
  Assert.assertEquals(header.hashCode(),result);
}
