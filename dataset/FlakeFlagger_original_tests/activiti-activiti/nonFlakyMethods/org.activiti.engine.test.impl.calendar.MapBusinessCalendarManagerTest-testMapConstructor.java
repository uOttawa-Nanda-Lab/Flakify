public void testMapConstructor(){
  Map<String,BusinessCalendar> calendars=new HashMap<String,BusinessCalendar>(1);
  CycleBusinessCalendar calendar=new CycleBusinessCalendar(null);
  calendars.put("someKey",calendar);
  MapBusinessCalendarManager businessCalendarManager=new MapBusinessCalendarManager(calendars);
  assertTrue(businessCalendarManager.getBusinessCalendar("someKey").equals(calendar));
}
