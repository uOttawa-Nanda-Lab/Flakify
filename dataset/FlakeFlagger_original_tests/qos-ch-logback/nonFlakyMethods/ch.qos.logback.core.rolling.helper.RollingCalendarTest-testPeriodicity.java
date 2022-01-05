public void testPeriodicity(){
{
    RollingCalendar rc=new RollingCalendar();
    assertEquals(PeriodicityType.TOP_OF_SECOND,rc.computePeriodicityType("yyyy-MM-dd_HH_mm_ss"));
  }
{
    RollingCalendar rc=new RollingCalendar();
    assertEquals(PeriodicityType.TOP_OF_MINUTE,rc.computePeriodicityType("yyyy-MM-dd_HH_mm"));
  }
{
    RollingCalendar rc=new RollingCalendar();
    assertEquals(PeriodicityType.TOP_OF_HOUR,rc.computePeriodicityType("yyyy-MM-dd_HH"));
  }
{
    RollingCalendar rc=new RollingCalendar();
    assertEquals(PeriodicityType.TOP_OF_DAY,rc.computePeriodicityType("yyyy-MM-dd"));
  }
{
    RollingCalendar rc=new RollingCalendar();
    assertEquals(PeriodicityType.TOP_OF_MONTH,rc.computePeriodicityType("yyyy-MM"));
  }
}
