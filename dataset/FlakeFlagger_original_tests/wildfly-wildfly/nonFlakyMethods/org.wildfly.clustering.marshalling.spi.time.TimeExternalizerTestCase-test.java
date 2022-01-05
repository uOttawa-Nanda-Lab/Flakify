@Test public void test() throws ClassNotFoundException, IOException {
  new EnumExternalizerTester<>(DefaultExternalizer.DAY_OF_WEEK.cast(DayOfWeek.class)).test();
  new EnumExternalizerTester<>(DefaultExternalizer.MONTH.cast(Month.class)).test();
  new ExternalizerTester<>(DefaultExternalizer.DURATION.cast(Duration.class)).test(Duration.between(Instant.EPOCH,Instant.now()));
  new ExternalizerTester<>(DefaultExternalizer.INSTANT.cast(Instant.class)).test(Instant.now());
  new ExternalizerTester<>(DefaultExternalizer.LOCAL_DATE.cast(LocalDate.class)).test(LocalDate.now());
  new ExternalizerTester<>(DefaultExternalizer.LOCAL_DATE_TIME.cast(LocalDateTime.class)).test(LocalDateTime.now());
  new ExternalizerTester<>(DefaultExternalizer.LOCAL_TIME.cast(LocalTime.class)).test(LocalTime.now());
  new ExternalizerTester<>(DefaultExternalizer.MONTH_DAY.cast(MonthDay.class)).test(MonthDay.now());
  new ExternalizerTester<>(DefaultExternalizer.PERIOD.cast(Period.class)).test(Period.between(LocalDate.ofEpochDay(0),LocalDate.now()));
  new ExternalizerTester<>(DefaultExternalizer.YEAR.cast(Year.class)).test(Year.now());
  new ExternalizerTester<>(DefaultExternalizer.YEAR_MONTH.cast(YearMonth.class)).test(YearMonth.now());
  new ExternalizerTester<>(DefaultExternalizer.ZONE_OFFSET.cast(ZoneOffset.class)).test(ZoneOffset.UTC);
  new ExternalizerTester<>(DefaultExternalizer.ZONE_ID.cast(ZoneId.class)).test(ZoneId.of("America/New_York"));
}
