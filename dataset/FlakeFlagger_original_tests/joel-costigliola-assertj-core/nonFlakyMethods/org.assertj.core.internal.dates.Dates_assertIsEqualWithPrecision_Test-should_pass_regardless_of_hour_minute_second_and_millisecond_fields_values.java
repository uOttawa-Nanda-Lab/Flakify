@Test public void should_pass_regardless_of_hour_minute_second_and_millisecond_fields_values(){
  AssertionInfo info=someInfo();
  Date other=parseDatetimeWithMs("2011-09-27T17:24:35.999");
  dates.assertIsEqualWithPrecision(info,actual,other,TimeUnit.HOURS);
  actual=parseDatetimeWithMs("2011-09-27T13:23:35.999");
  other=parseDatetimeWithMs("2011-09-27T01:23:35.999");
  dates.assertIsEqualWithPrecision(info,actual,other,TimeUnit.HOURS);
}
