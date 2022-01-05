@Test public void assertMonitorWithCommand() throws IOException {
  initJob();
  assertNotNull(SocketUtils.sendCommand(MonitorService.DUMP_COMMAND,MONITOR_PORT));
  assertNull(SocketUtils.sendCommand("unknown_command",MONITOR_PORT));
}
