@Test public void testGetTimestamp(){
  NullPointerException npe=new NullPointerException("npe");
  ExceptionEvent ee=new ExceptionEvent(npe);
  Assert.assertNotNull(ee.getTimestamp());
  ee=new ExceptionEvent(npe,new Date(1234567890L));
  Assert.assertEquals(new Date(1234567890L),ee.getTimestamp());
}
