@Test public void date_assertion_should_support_timestamp_string_representation(){
  Date date=new Date();
  Timestamp timestamp=new Timestamp(date.getTime());
  String timestampAsString=timestamp.toString();
  assertThat(date).isEqualTo(timestampAsString);
}
