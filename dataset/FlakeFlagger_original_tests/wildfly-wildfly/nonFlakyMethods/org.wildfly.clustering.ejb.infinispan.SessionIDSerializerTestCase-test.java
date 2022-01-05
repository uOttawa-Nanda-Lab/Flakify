@Test public void test() throws ClassNotFoundException, IOException {
  UUID uuid=UUID.randomUUID();
  new ExternalizerTester<>(new UUIDSessionIDExternalizer()).test(new UUIDSessionID(uuid));
  ByteBuffer buffer=ByteBuffer.wrap(new byte[20]);
  buffer.putInt(0x07000000);
  buffer.putLong(uuid.getMostSignificantBits());
  buffer.putLong(uuid.getLeastSignificantBits());
  new ExternalizerTester<>(new BasicSessionIDExternalizer()).test(SessionID.createSessionID(buffer.array()));
  buffer=ByteBuffer.wrap(new byte[16]);
  buffer.putLong(uuid.getMostSignificantBits());
  buffer.putLong(uuid.getLeastSignificantBits());
  new ExternalizerTester<>(new UnknownSessionIDExternalizer()).test(SessionID.createSessionID(buffer.array()));
}
