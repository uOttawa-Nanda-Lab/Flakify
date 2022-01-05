@Test @InSequence(4) public void testAccess() throws Exception {
  URL wsdlURL=new URL(this.url.toExternalForm() + "ws-example?wsdl");
  QName serviceName=new QName("http://webservices.smoke.test.as.jboss.org/","EndpointService");
  Service service=Service.create(wsdlURL,serviceName);
  Endpoint port=(Endpoint)service.getPort(Endpoint.class);
  String echo=port.echo("Foo");
  assertThat("Echoing Foo should return Foo not " + echo,echo,is("Foo"));
}
