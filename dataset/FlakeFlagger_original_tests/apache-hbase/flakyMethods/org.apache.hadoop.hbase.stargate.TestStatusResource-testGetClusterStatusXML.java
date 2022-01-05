public void testGetClusterStatusXML() throws IOException, JAXBException {
  Response response=client.get("/status/cluster",MIMETYPE_XML);
  assertEquals(response.getCode(),200);
  StorageClusterStatusModel model=(StorageClusterStatusModel)context.createUnmarshaller().unmarshal(new ByteArrayInputStream(response.getBody()));
  validate(model);
}
