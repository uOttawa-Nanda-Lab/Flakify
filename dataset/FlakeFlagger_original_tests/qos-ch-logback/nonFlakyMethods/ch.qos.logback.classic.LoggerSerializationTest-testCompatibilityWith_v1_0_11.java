@Test public void testCompatibilityWith_v1_0_11() throws IOException, ClassNotFoundException {
  FileInputStream fis=new FileInputStream(SERIALIZATION_PREFIX + "logger_v1.0.11.ser");
  ObjectInputStream ois=new ObjectInputStream(fis);
  Logger a=(Logger)ois.readObject();
  ois.close();
  assertEquals("a",a.getName());
}
