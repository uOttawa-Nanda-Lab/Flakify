@Test public void assertStart(){
  rescheduleListenerManager.start();
  verify(jobNodeStorage).addDataListener(ArgumentMatchers.<RescheduleListenerManager.CronSettingAndJobEventChangedJobListener>any());
}
