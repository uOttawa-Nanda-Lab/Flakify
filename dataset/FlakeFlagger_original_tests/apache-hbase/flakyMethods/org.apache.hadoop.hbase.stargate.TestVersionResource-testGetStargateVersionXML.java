public void testGetStargateVersionXML() throws IOException, JAXBException {
  Response response=client.get("/version",MIMETYPE_XML);
  assertTrue(response.getCode() == 200);
  VersionModel model=(VersionModel)context.createUnmarshaller().unmarshal(new ByteArrayInputStream(response.getBody()));
  validate(model);
  LOG.info("success retrieving Stargate version as XML");
}
