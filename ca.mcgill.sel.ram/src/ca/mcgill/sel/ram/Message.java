/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.Message#getSendEvent <em>Send Event</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Message#getReceiveEvent <em>Receive Event</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Message#getSignature <em>Signature</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Message#getAssignTo <em>Assign To</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Message#getMessageSort <em>Message Sort</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Message#getArguments <em>Arguments</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Message#getInteraction <em>Interaction</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Message#getReturns <em>Returns</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Message#isSelfMessage <em>Self Message</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Message#getLocalProperties <em>Local Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getMessage()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='validSelfMessage argumentsSpecified createMessageIsFirst returnsSpecified boundariesNotCrossed validReturns deleteMessageIsLast assignToAllowed noCrossingMessages'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot validSelfMessage='Tuple {\n\tmessage : String = \'receiveEvent of a self message may not come before the sendEvent\',\n\tstatus : Boolean = if not self.receiveEvent.oclIsUndefined() and self.receiveEvent.oclIsKindOf(MessageOccurrenceSpecification) and not self.sendEvent.oclIsUndefined() and self.sendEvent.oclIsKindOf(MessageOccurrenceSpecification) then let sendEvent : InteractionFragment = self.sendEvent.oclAsType(InteractionFragment) in let receiveEvent : InteractionFragment = self.receiveEvent.oclAsType(InteractionFragment) in if sendEvent.covered->asOrderedSet()->at(1) = receiveEvent.covered->asOrderedSet()->at(1) then sendEvent.container.fragments->indexOf(self.sendEvent) < receiveEvent.container.fragments->indexOf(self.receiveEvent) else true endif else true endif\n}.status' argumentsSpecified='Tuple {\n\tmessage : String = \'All arguments of the operation must be specified\',\n\tstatus : Boolean = if self.messageSort <> MessageSort::reply and not self.sendEvent.oclIsUndefined() and self.sendEvent.oclIsKindOf(MessageOccurrenceSpecification) and not self.signature.oclIsUndefined() then let container : FragmentContainer = self.sendEvent.oclAsType(MessageOccurrenceSpecification).container in if container.fragments->includes(self.sendEvent) then self.signature.parameters->size() = self.arguments->size() and self.signature.parameters->forAll(currentParameter : Parameter | self.arguments->one(argument : ParameterValueMapping | argument.parameter = currentParameter)) else true endif else true endif\n}.status' createMessageIsFirst='Tuple {\n\tmessage : String = \'The create message occurrence may not come after any other occurrence on this lifeline\',\n\tstatus : Boolean = if self.messageSort = MessageSort::createMessage then if not self.receiveEvent.oclIsUndefined() and not self.receiveEvent.oclAsType(InteractionFragment).covered->isEmpty() then let event : InteractionFragment = self.receiveEvent.oclAsType(InteractionFragment) in event.covered->asOrderedSet()->at(1).coveredBy->forAll(fragment : InteractionFragment | if event.container.fragments->includes(fragment) then event.container.fragments->indexOf(fragment) >= event.container.fragments->indexOf(event) else true endif) else true endif else true endif\n}.status' returnsSpecified='Tuple {\n\tmessage : String = \'Reply message must have returns specified if return type is not void\',\n\tstatus : Boolean = if self.messageSort = MessageSort::reply then if not self.signature.returnType.oclIsTypeOf(RVoid) and self.signature.operationType = OperationType::Normal then not self.returns.oclIsUndefined() else self.returns.oclIsUndefined() endif else true endif\n}.status' boundariesNotCrossed='Tuple {\n\tmessage : String = \'Messages may not cross boundaries of CombinedFragments or their operands\',\n\tstatus : Boolean = let send : MessageOccurrenceSpecification = self.sendEvent in let receive : MessageOccurrenceSpecification = self.receiveEvent in if send.container.oclIsTypeOf(InteractionOperand) and receive.container.oclIsTypeOf(InteractionOperand) then send.container.oclAsType(InteractionOperand).CombinedFragment.covered->includes(send.covered->asOrderedSet()->at(1)) and receive.container.oclAsType(InteractionOperand).CombinedFragment.covered->includes(receive.covered->asOrderedSet()->at(1)) else true endif\n}.status' validReturns='Tuple {\n\tmessage : String = \'Returns may only be specified if message sort is reply message\',\n\tstatus : Boolean = if not self.returns.oclIsUndefined() then self.messageSort = MessageSort::reply else true endif\n}.status' deleteMessageIsLast='Tuple {\n\tmessage : String = \'The destruction occurrence may not come before any other occurrence on this lifeline\',\n\tstatus : Boolean = if self.messageSort = MessageSort::deleteMessage then if not self.receiveEvent.oclIsUndefined() and not self.receiveEvent.oclAsType(InteractionFragment).covered->isEmpty() then let event : InteractionFragment = self.receiveEvent.oclAsType(InteractionFragment) in event.covered->asOrderedSet()->at(1).coveredBy->forAll(fragment : InteractionFragment | if event.container.fragments->includes(fragment) then event.container.fragments->indexOf(fragment) <= event.container.fragments->indexOf(event) else true endif) else true endif else true endif\n}.status' assignToAllowed='Tuple {\n\tmessage : String = \'assignTo may not be specified for operations whose return type is void\',\n\tstatus : Boolean = if self.signature.returnType.oclIsTypeOf(RVoid) then self.assignTo = null else true endif\n}.status' noCrossingMessages='Tuple {\n\tmessage : String = \'Messages may not cross each other, MessageEnds of one message must come one after the other (also, send must come before receive)\',\n\tstatus : Boolean = if not self.receiveEvent.oclIsUndefined() and self.receiveEvent.oclIsKindOf(MessageOccurrenceSpecification) and not self.sendEvent.oclIsUndefined() and self.sendEvent.oclIsKindOf(MessageOccurrenceSpecification) then let container : FragmentContainer = self.receiveEvent.oclAsType(MessageOccurrenceSpecification).container in let indexDifference : Integer = container.fragments->indexOf(self.receiveEvent) - container.fragments->indexOf(self.sendEvent) in indexDifference = 1 else true endif\n}.status'"
 * @generated
 */
public interface Message extends EObject {
    /**
     * Returns the value of the '<em><b>Send Event</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Send Event</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Send Event</em>' reference.
     * @see #setSendEvent(MessageEnd)
     * @see ca.mcgill.sel.ram.RamPackage#getMessage_SendEvent()
     * @model required="true"
     * @generated
     */
    MessageEnd getSendEvent();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Message#getSendEvent <em>Send Event</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Send Event</em>' reference.
     * @see #getSendEvent()
     * @generated
     */
    void setSendEvent(MessageEnd value);

    /**
     * Returns the value of the '<em><b>Receive Event</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Receive Event</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Receive Event</em>' reference.
     * @see #setReceiveEvent(MessageEnd)
     * @see ca.mcgill.sel.ram.RamPackage#getMessage_ReceiveEvent()
     * @model required="true"
     * @generated
     */
    MessageEnd getReceiveEvent();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Message#getReceiveEvent <em>Receive Event</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Receive Event</em>' reference.
     * @see #getReceiveEvent()
     * @generated
     */
    void setReceiveEvent(MessageEnd value);

    /**
     * Returns the value of the '<em><b>Signature</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Signature</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Signature</em>' reference.
     * @see #setSignature(Operation)
     * @see ca.mcgill.sel.ram.RamPackage#getMessage_Signature()
     * @model required="true"
     * @generated
     */
    Operation getSignature();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Message#getSignature <em>Signature</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Signature</em>' reference.
     * @see #getSignature()
     * @generated
     */
    void setSignature(Operation value);

    /**
     * Returns the value of the '<em><b>Assign To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assign To</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Assign To</em>' reference.
     * @see #setAssignTo(StructuralFeature)
     * @see ca.mcgill.sel.ram.RamPackage#getMessage_AssignTo()
     * @model
     * @generated
     */
    StructuralFeature getAssignTo();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Message#getAssignTo <em>Assign To</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Assign To</em>' reference.
     * @see #getAssignTo()
     * @generated
     */
    void setAssignTo(StructuralFeature value);

    /**
     * Returns the value of the '<em><b>Message Sort</b></em>' attribute.
     * The literals are from the enumeration {@link ca.mcgill.sel.ram.MessageSort}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message Sort</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Sort</em>' attribute.
     * @see ca.mcgill.sel.ram.MessageSort
     * @see #setMessageSort(MessageSort)
     * @see ca.mcgill.sel.ram.RamPackage#getMessage_MessageSort()
     * @model required="true"
     * @generated
     */
    MessageSort getMessageSort();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Message#getMessageSort <em>Message Sort</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Sort</em>' attribute.
     * @see ca.mcgill.sel.ram.MessageSort
     * @see #getMessageSort()
     * @generated
     */
    void setMessageSort(MessageSort value);

    /**
     * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.ParameterValueMapping}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Arguments</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getMessage_Arguments()
     * @model containment="true"
     * @generated
     */
    EList<ParameterValueMapping> getArguments();

    /**
     * Returns the value of the '<em><b>Interaction</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.ram.Interaction#getMessages <em>Messages</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interaction</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Interaction</em>' container reference.
     * @see #setInteraction(Interaction)
     * @see ca.mcgill.sel.ram.RamPackage#getMessage_Interaction()
     * @see ca.mcgill.sel.ram.Interaction#getMessages
     * @model opposite="messages" required="true" transient="false"
     * @generated
     */
    Interaction getInteraction();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Message#getInteraction <em>Interaction</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Interaction</em>' container reference.
     * @see #getInteraction()
     * @generated
     */
    void setInteraction(Interaction value);

    /**
     * Returns the value of the '<em><b>Returns</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Returns</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Returns</em>' containment reference.
     * @see #setReturns(ValueSpecification)
     * @see ca.mcgill.sel.ram.RamPackage#getMessage_Returns()
     * @model containment="true"
     * @generated
     */
    ValueSpecification getReturns();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Message#getReturns <em>Returns</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Returns</em>' containment reference.
     * @see #getReturns()
     * @generated
     */
    void setReturns(ValueSpecification value);

    /**
     * Returns the value of the '<em><b>Self Message</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Self Message</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Self Message</em>' attribute.
     * @see ca.mcgill.sel.ram.RamPackage#getMessage_SelfMessage()
     * @model default="false" required="true" transient="true" changeable="false" volatile="true" derived="true"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot derivation='if self.sendEvent.oclIsKindOf(MessageOccurrenceSpecification) and self.receiveEvent.oclIsKindOf(MessageOccurrenceSpecification) then self.sendEvent.oclAsType(MessageOccurrenceSpecification).covered->asOrderedSet()->at(1) = self.receiveEvent.oclAsType(MessageOccurrenceSpecification).covered->asOrderedSet()->at(1) else false endif'"
     * @generated
     */
    boolean isSelfMessage();

    /**
     * Returns the value of the '<em><b>Local Properties</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.TemporaryProperty}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Local Properties</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Local Properties</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getMessage_LocalProperties()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<TemporaryProperty> getLocalProperties();

} // Message
