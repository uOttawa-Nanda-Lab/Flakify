@Test public void smoke(){
  assertEquals(SyslogConstants.DEBUG_SEVERITY,LevelToSyslogSeverity.convert(createEventOfLevel(Level.TRACE)));
  assertEquals(SyslogConstants.DEBUG_SEVERITY,LevelToSyslogSeverity.convert(createEventOfLevel(Level.DEBUG)));
  assertEquals(SyslogConstants.INFO_SEVERITY,LevelToSyslogSeverity.convert(createEventOfLevel(Level.INFO)));
  assertEquals(SyslogConstants.WARNING_SEVERITY,LevelToSyslogSeverity.convert(createEventOfLevel(Level.WARN)));
  assertEquals(SyslogConstants.ERROR_SEVERITY,LevelToSyslogSeverity.convert(createEventOfLevel(Level.ERROR)));
}
