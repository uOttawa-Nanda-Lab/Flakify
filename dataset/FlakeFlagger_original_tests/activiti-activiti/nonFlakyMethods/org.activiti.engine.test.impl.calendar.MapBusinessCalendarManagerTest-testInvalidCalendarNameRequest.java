public void testInvalidCalendarNameRequest(){
  @SuppressWarnings("unchecked") MapBusinessCalendarManager businessCalendarManager=new MapBusinessCalendarManager(Collections.EMPTY_MAP);
  try {
    businessCalendarManager.getBusinessCalendar("INVALID");
    fail("ActivitiException expected");
  }
 catch (  ActivitiException e) {
    assertThat(e.getMessage(),containsString("INVALID does not exist"));
  }
}
