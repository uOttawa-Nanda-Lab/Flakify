@Test public void persistValue(){
  Settings settings=new Settings();
  assertFalse(settings.persistValue(Settings.ROUND_TRIP_TIME));
  settings.set(Settings.ROUND_TRIP_TIME,0,0);
  assertFalse(settings.persistValue(Settings.ROUND_TRIP_TIME));
  settings.set(Settings.ROUND_TRIP_TIME,PERSISTED,0);
  assertFalse(settings.persistValue(Settings.ROUND_TRIP_TIME));
  settings.set(Settings.ROUND_TRIP_TIME,PERSIST_VALUE,0);
  assertTrue(settings.persistValue(Settings.ROUND_TRIP_TIME));
  settings.set(Settings.ROUND_TRIP_TIME,PERSIST_VALUE | PERSISTED,0);
  assertTrue(settings.persistValue(Settings.ROUND_TRIP_TIME));
  settings.set(Settings.ROUND_TRIP_TIME,PERSISTED,0);
  assertFalse(settings.persistValue(Settings.ROUND_TRIP_TIME));
  settings.set(Settings.ROUND_TRIP_TIME,0,0);
  assertFalse(settings.persistValue(Settings.ROUND_TRIP_TIME));
}
