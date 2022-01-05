@Test public void test() throws ClassNotFoundException, IOException {
  new ExternalizerTester<>(DefaultExternalizer.DATE.cast(Date.class)).test(Date.from(Instant.now()));
  new ExternalizerTester<>(DefaultExternalizer.SQL_DATE.cast(java.sql.Date.class)).test(java.sql.Date.valueOf(LocalDate.now()));
  new ExternalizerTester<>(DefaultExternalizer.SQL_TIME.cast(java.sql.Time.class)).test(java.sql.Time.valueOf(LocalTime.now()));
  new ExternalizerTester<>(DefaultExternalizer.SQL_TIMESTAMP.cast(java.sql.Timestamp.class)).test(java.sql.Timestamp.valueOf(LocalDateTime.now()));
  ExternalizerTester<Calendar> calendarTester=new ExternalizerTester<>(DefaultExternalizer.CALENDAR.cast(Calendar.class));
  calendarTester.test(Calendar.getInstance());
  calendarTester.test(new Calendar.Builder().setLenient(false).setLocale(Locale.FRANCE).build());
  calendarTester.test(Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"),Locale.JAPAN));
  calendarTester.test(Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"),Locale.forLanguageTag("th_TH")));
}
