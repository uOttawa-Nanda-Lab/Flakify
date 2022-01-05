@Test public void testTimeUUIDAsByteArray(){
  UUID uuid=TimeUUIDUtils.getUniqueTimeUUIDinMillis();
  UUID uuidAfterConversion=TimeUUIDUtils.toUUID(TimeUUIDUtils.asByteArray(uuid));
  assertEquals(uuid,uuidAfterConversion);
  long timeInUUID=TimeUUIDUtils.getTimeFromUUID(TimeUUIDUtils.asByteArray(uuid));
  assertEquals(uuid.timestamp(),timeInUUID);
}
