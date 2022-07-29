package org.activiti.engine.test.image;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.exception.ActivitiImageException;
import org.activiti.image.exception.ActivitiInterchangeInfoNotFoundException;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.commons.io.IOUtils;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.*;

public class ProcessDiagramGeneratorTest extends PluggableActivitiTestCase {

    /**
	 * Test that when the diagram is generated for a model without graphic info then the default diagram image is returned or the ActivitiInterchangeInfoNotFoundException is thrown depending on the value of the generateDefaultDiagram parameter.
	 */@Deployment public void testGenerateDefaultDiagram() throws Exception{String id=repositoryService.createProcessDefinitionQuery().processDefinitionKey("fixSystemFailure").singleResult().getId();BpmnModel bpmnModel=repositoryService.getBpmnModel(id);ProcessDiagramGenerator imageGenerator=new DefaultProcessDiagramGenerator();String activityFontName=imageGenerator.getDefaultActivityFontName();String labelFontName=imageGenerator.getDefaultLabelFontName();String annotationFontName=imageGenerator.getDefaultAnnotationFontName();try (final InputStream resourceStream=imageGenerator.generateDiagram(bpmnModel,emptyList(),emptyList(),activityFontName,labelFontName,annotationFontName,true)){assertThat(resourceStream).isNotNull();byte[] diagram=IOUtils.toByteArray(resourceStream);assertThat(diagram).isNotNull();try (InputStream imageStream=getClass().getResourceAsStream(imageGenerator.getDefaultDiagramImageFileName())){assertThat(diagram).isEqualTo(IOUtils.toByteArray(imageStream));} } assertThatExceptionOfType(ActivitiInterchangeInfoNotFoundException.class).isThrownBy(()->imageGenerator.generateDiagram(bpmnModel,emptyList(),emptyList(),activityFontName,labelFontName,annotationFontName,false)).withMessage("No interchange information found.");assertThatExceptionOfType(ActivitiImageException.class).isThrownBy(()->imageGenerator.generateDiagram(bpmnModel,emptyList(),emptyList(),activityFontName,labelFontName,annotationFontName,true,"invalid-file-name")).withMessage("Error occurred while getting default diagram image from file: invalid-file-name");}

    private void checkDiagramElements(List<String> elementIdList, SVGOMDocument svg) {
        for (String elementId : elementIdList) {
            assertNotNull(svg.getElementById(elementId));
        }
    }

    private SVGOMDocument parseXml(InputStream resourceStream) throws Exception {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        return (SVGOMDocument) factory.createDocument(null, resourceStream);
    }
}
