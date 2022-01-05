@Test public void test() throws ClassNotFoundException, IOException {
  new ExternalizerTester<>(DefaultExternalizer.CURRENCY.cast(Currency.class)).test(Currency.getInstance(Locale.US));
  new ExternalizerTester<>(DefaultExternalizer.LOCALE.cast(Locale.class)).test(Locale.US);
  new ExternalizerTester<>(DefaultExternalizer.NATURAL_ORDER_COMPARATOR.cast(Comparator.class)).test(Comparator.naturalOrder());
  new ExternalizerTester<>(DefaultExternalizer.OPTIONAL.cast(Optional.class)).test(Optional.empty());
  new ExternalizerTester<>(DefaultExternalizer.OPTIONAL.cast(Optional.class)).test(Optional.of(UUID.randomUUID()));
  new ExternalizerTester<>(DefaultExternalizer.REVERSE_ORDER_COMPARATOR.cast(Comparator.class)).test(Comparator.reverseOrder());
  new EnumExternalizerTester<>(DefaultExternalizer.TIME_UNIT.cast(TimeUnit.class)).test();
  new ExternalizerTester<>(DefaultExternalizer.TIME_ZONE.cast(TimeZone.class)).test(TimeZone.getDefault());
  new ExternalizerTester<>(DefaultExternalizer.TIME_ZONE.cast(TimeZone.class)).test(TimeZone.getTimeZone("America/New_York"));
  new ExternalizerTester<>(DefaultExternalizer.UUID.cast(UUID.class)).test(UUID.randomUUID());
}
