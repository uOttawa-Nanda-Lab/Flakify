/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.standalone.validation;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.test.util.TestProcessUtil;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.activiti.validation.validator.Problems;
import org.activiti.validation.validator.ValidatorSetNames;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**

 */
public class DefaultProcessValidatorTest {

  protected ProcessValidator processValidator;

  @Before
  public void setupProcessValidator() {
    ProcessValidatorFactory processValidatorFactory = new ProcessValidatorFactory();
    this.processValidator = processValidatorFactory.createDefaultProcessValidator();
  }

  @Test public void testWarningError() throws UnsupportedEncodingException,XMLStreamException{String flowWithoutConditionNoDefaultFlow="<?xml version='1.0' encoding='UTF-8'?>" + "<definitions id='definitions' xmlns='http://www.omg.org/spec/BPMN/20100524/MODEL' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:activiti='http://activiti.org/bpmn' targetNamespace='Examples'>" + "  <process id='exclusiveGwDefaultSequenceFlow'> " + "    <startEvent id='theStart' /> " + "    <sequenceFlow id='flow1' sourceRef='theStart' targetRef='exclusiveGw' /> " + "    <exclusiveGateway id='exclusiveGw' name='Exclusive Gateway' /> " + "    <sequenceFlow id='flow2' sourceRef='exclusiveGw' targetRef='theTask1'> " + "      <conditionExpression xsi:type='tFormalExpression'>${input == 1}</conditionExpression> " + "    </sequenceFlow> " + "    <sequenceFlow id='flow3' sourceRef='exclusiveGw' targetRef='theTask2'/> " + "    <sequenceFlow id='flow4' sourceRef='exclusiveGw' targetRef='theTask2'/> " + "    <userTask id='theTask1' name='Input is one' /> " + "    <userTask id='theTask2' name='Default input' /> " + "  </process>" + "</definitions>";XMLInputFactory xif=XMLInputFactory.newInstance();InputStreamReader in=new InputStreamReader(new ByteArrayInputStream(flowWithoutConditionNoDefaultFlow.getBytes()),"UTF-8");XMLStreamReader xtr=xif.createXMLStreamReader(in);BpmnModel bpmnModel=new BpmnXMLConverter().convertToBpmnModel(xtr);Assert.assertNotNull(bpmnModel);List<ValidationError> allErrors=processValidator.validate(bpmnModel);Assert.assertEquals(1,allErrors.size());Assert.assertTrue(allErrors.get(0).isWarning());}

  protected void assertCommonProblemFieldForActivity(ValidationError error) {
    assertProcessElementError(error);

    Assert.assertNotNull(error.getActivityId());
    Assert.assertNotNull(error.getActivityName());

    Assert.assertTrue(error.getActivityId().length() > 0);
    Assert.assertTrue(error.getActivityName().length() > 0);
  }

  protected void assertCommonErrorFields(ValidationError error) {
    Assert.assertNotNull(error.getValidatorSetName());
    Assert.assertNotNull(error.getProblem());
    Assert.assertNotNull(error.getDefaultDescription());
    Assert.assertTrue(error.getXmlLineNumber() > 0);
    Assert.assertTrue(error.getXmlColumnNumber() > 0);
  }

  protected void assertProcessElementError(ValidationError error) {
    assertCommonErrorFields(error);
    Assert.assertEquals("invalidProcess", error.getProcessDefinitionId());
    Assert.assertEquals("The invalid process", error.getProcessDefinitionName());
  }

  protected List<ValidationError> findErrors(List<ValidationError> errors, String validatorSetName, String problemName, int expectedNrOfProblems) {
    List<ValidationError> results = findErrors(errors, validatorSetName, problemName);
    Assert.assertEquals(expectedNrOfProblems, results.size());
    for (ValidationError result : results) {
      Assert.assertEquals(validatorSetName, result.getValidatorSetName());
      Assert.assertEquals(problemName, result.getProblem());
    }
    return results;
  }

  protected List<ValidationError> findErrors(List<ValidationError> errors, String validatorSetName, String problemName) {
    List<ValidationError> results = new ArrayList<ValidationError>();
    for (ValidationError error : errors) {
      if (error.getValidatorSetName().equals(validatorSetName) && error.getProblem().equals(problemName)) {
        results.add(error);
      }
    }
    return results;
  }

}
