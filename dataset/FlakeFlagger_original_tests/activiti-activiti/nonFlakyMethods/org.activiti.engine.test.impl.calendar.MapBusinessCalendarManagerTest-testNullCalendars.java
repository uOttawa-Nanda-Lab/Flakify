public void testNullCalendars(){
  try {
    new MapBusinessCalendarManager(null);
    fail("AssertionError expected");
  }
 catch (  IllegalArgumentException e) {
  }
}
