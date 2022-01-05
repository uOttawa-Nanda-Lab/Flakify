@Test(expected=IOException.class) public void assertMonitorWithDumpCommand() throws IOException {
  SocketUtils.sendCommand(MonitorService.DUMP_COMMAND,9000);
}
