@Test public void testSendingHolidayRequest(){
  final String request="<hr:HolidayRequest xmlns:hr=\"http://mycompany.com/hr/schemas\">" + "   <hr:Holiday>" + "      <hr:StartDate>2013-10-20</hr:StartDate>"+ "      <hr:EndDate>2013-11-22</hr:EndDate>"+ "   </hr:Holiday>"+ "   <hr:Employee>"+ "      <hr:Number>1</hr:Number>"+ "      <hr:FirstName>John</hr:FirstName>"+ "      <hr:LastName>Doe</hr:LastName>"+ "   </hr:Employee>"+ "</hr:HolidayRequest>";
  StreamSource source=new StreamSource(new StringReader(request));
  StreamResult result=new StreamResult(System.out);
  this.webServiceTemplate.sendSourceAndReceiveToResult(source,result);
  assertThat(this.output.toString(),containsString("Booking holiday for"));
}
