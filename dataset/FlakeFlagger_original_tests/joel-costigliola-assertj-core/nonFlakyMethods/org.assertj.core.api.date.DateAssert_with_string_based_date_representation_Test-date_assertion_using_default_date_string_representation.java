@Test public void date_assertion_using_default_date_string_representation(){
  final Date date1timeWithMS=parseDatetimeWithMs("2003-04-26T03:01:02.999");
  assertThat(date1timeWithMS).isEqualTo("2003-04-26T03:01:02.999");
  final Date datetime=parseDatetime("2003-04-26T03:01:02");
  assertThat(datetime).isEqualTo("2003-04-26T03:01:02.000");
  assertThat(datetime).isEqualTo("2003-04-26T03:01:02");
  final Date date=Dates.parse("2003-04-26");
  assertThat(date).isEqualTo("2003-04-26");
  assertThat(date).isEqualTo("2003-04-26T00:00:00");
  assertThat(date).isEqualTo("2003-04-26T00:00:00.000");
}
