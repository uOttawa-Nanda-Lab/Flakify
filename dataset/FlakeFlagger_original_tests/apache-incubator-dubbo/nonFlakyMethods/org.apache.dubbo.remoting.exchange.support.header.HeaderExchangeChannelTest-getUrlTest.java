@Test public void getUrlTest(){
  Assert.assertEquals(header.getUrl(),URL.valueOf("dubbo://localhost:20880"));
}
