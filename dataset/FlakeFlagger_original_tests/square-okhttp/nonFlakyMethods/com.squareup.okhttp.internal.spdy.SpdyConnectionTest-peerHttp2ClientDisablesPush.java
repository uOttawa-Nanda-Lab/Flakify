@Test public void peerHttp2ClientDisablesPush() throws Exception {
  boolean client=false;
  Settings settings=new Settings();
  settings.set(Settings.ENABLE_PUSH,0,0);
  SpdyConnection connection=sendHttp2SettingsAndCheckForAck(client,settings);
synchronized (connection) {
    assertFalse(connection.peerSettings.getEnablePush(true));
  }
}
