/*
 * Copyright 2017-2020 FIX Protocol Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package de.beckdev.quickfix.validation;

import io.fixprotocol._2020.orchestra.repository.*;

import java.util.Collections;
import java.util.List;

/**
 * Accessor methods for a Repository
 *
 * @author Don Mendelson
 */
public class RepositoryAccessor {

  private final Repository repository;

  /**
   * Constructor wraps a populated Repository
   * @param repository an Orchestra Repository object
   */
  public RepositoryAccessor(Repository repository) {
    this.repository = repository;
  }

  /**
   * Get a Code Set by name
   * 
   * @param name Code Set name
   * @param scenario scenario name
   * @return A Code Set or {@code null} if not found
   */
  public CodeSetType getCodeset(String name, String scenario) {
    final List<CodeSetType> codeSetList = repository.getCodeSets().getCodeSet();
    for (final CodeSetType codeSet : codeSetList) {
      if (name.equals(codeSet.getName()) && scenario.equals(codeSet.getScenario())) {
        return codeSet;
      }
    }
    return null;
  }

  /**
   * Get a component by a reference to it
   * 
   * @param componentRefType reference to a component
   * @return A component or {@code null} if not found
   */
  public ComponentType getComponent(ComponentRefType componentRefType) {
    final List<ComponentType> components = repository.getComponents().getComponent();
    for (final ComponentType component : components) {
      if (component.getId().equals(componentRefType.getId())
          && component.getScenario().equals(componentRefType.getScenario())) {
        return component;
      }
    }
    return null;
  }

  /**
   * Get a datatype by name
   * 
   * @param name datatype name
   * @return A datatype or {@code null} if not found
   */
  public Datatype getDatatype(String name) {
    final Datatypes datatypes = repository.getDatatypes();
    final List<Datatype> datatypeList = datatypes.getDatatype();
    for (final Datatype datatype : datatypeList) {
      if (name.equals(datatype.getName())) {
        return datatype;
      }
    }
    return null;
  }

  /**
   * Get the datatype of a field by its ID (tag)
   * 
   * @param id field ID
   * @param scenario scenario name
   * @return name of the datatype or {@code null} if the field is not found
   */
  public String getFieldDatatype(int id, String scenario) {
    final List<FieldType> fields = repository.getFields().getField();
    for (final FieldType fieldType : fields) {
      if (fieldType.getId().intValue() == id && fieldType.getScenario().equals(scenario)) {
        return fieldType.getType();
      }
    }
    return null;
  }

  /**
   * Get the name of a field by its ID (tag)
   * 
   * @param id field ID
   * @param scenario scenario name
   * @return name of the field or {@code null} if the field is not found
   */
  public String getFieldName(int id, String scenario) {
    final List<FieldType> fields = repository.getFields().getField();
    for (final FieldType fieldType : fields) {
      if (fieldType.getId().intValue() == id && fieldType.getScenario().equals(scenario)) {
        return fieldType.getName();
      }
    }
    return null;
  }

  /**
   * Get a group given a reference to it
   * 
   * @param groupRefType group reference
   * @return a group or {@code null} if not found
   */
  public GroupType getGroup(GroupRefType groupRefType) {
    final List<GroupType> groups = repository.getGroups().getGroup();
    for (final GroupType group : groups) {
      if (group.getId().equals(groupRefType.getId())
          && group.getScenario().equals(groupRefType.getScenario())) {
        return group;
      }
    }
    return null;
  }

  /**
   * Get a message by its name and scenario name
   * 
   * @param name message name
   * @param scenario scenario name
   * @return a message or {@code null} if not found
   */
  public MessageType getMessage(String name, String scenario) {
    final List<MessageType> messageList = repository.getMessages().getMessage();
    for (final MessageType messageType : messageList) {
      if (name.equals(messageType.getName()) && scenario.equals(messageType.getScenario())) {

        return messageType;
      }
    }
    return null;
  }

  /**
   * Returns an immutable list of a message members
   * 
   * @param messageType a message
   * @return a combined list of members
   */
  public List<Object> getMessageMembers(MessageType messageType) {
    return Collections
        .unmodifiableList(messageType.getStructure().getComponentRefOrGroupRefOrFieldRef());
  }

}
