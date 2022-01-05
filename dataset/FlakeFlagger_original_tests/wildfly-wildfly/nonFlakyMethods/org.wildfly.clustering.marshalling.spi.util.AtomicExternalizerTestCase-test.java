@Test public void test() throws ClassNotFoundException, IOException {
  new ExternalizerTester<>(DefaultExternalizer.ATOMIC_BOOLEAN.cast(AtomicBoolean.class),(expected,actual) -> assertEquals(expected.get(),actual.get())).test(new AtomicBoolean(true));
  new ExternalizerTester<>(DefaultExternalizer.ATOMIC_INTEGER.cast(AtomicInteger.class),(expected,actual) -> assertEquals(expected.get(),actual.get())).test(new AtomicInteger(Integer.MAX_VALUE));
  new ExternalizerTester<>(DefaultExternalizer.ATOMIC_LONG.cast(AtomicLong.class),(expected,actual) -> assertEquals(expected.get(),actual.get())).test(new AtomicLong(Long.MAX_VALUE));
  new ExternalizerTester<>(DefaultExternalizer.ATOMIC_REFERENCE.cast(AtomicReference.class),(expected,actual) -> assertEquals(expected.get(),actual.get())).test(new AtomicReference<Object>(Boolean.TRUE));
}
