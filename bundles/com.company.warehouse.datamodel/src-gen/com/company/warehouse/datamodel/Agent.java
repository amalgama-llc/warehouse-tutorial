/**
 */
package com.company.warehouse.datamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.company.warehouse.datamodel.Agent#getId <em>Id</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Agent#getName <em>Name</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Agent#isIncluded <em>Included</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Agent#getBasePosition <em>Base Position</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Agent#getVelocity <em>Velocity</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Agent#getScenario <em>Scenario</em>}</li>
 * </ul>
 *
 * @see com.company.warehouse.datamodel.DatamodelPackage#getAgent()
 * @model
 * @generated
 */
public interface Agent extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getAgent_Id()
	 * @model id="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Agent#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getAgent_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Agent#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Included</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included</em>' attribute.
	 * @see #setIncluded(boolean)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getAgent_Included()
	 * @model default="true"
	 * @generated
	 */
	boolean isIncluded();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Agent#isIncluded <em>Included</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Included</em>' attribute.
	 * @see #isIncluded()
	 * @generated
	 */
	void setIncluded(boolean value);

	/**
	 * Returns the value of the '<em><b>Base Position</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Position</em>' reference.
	 * @see #setBasePosition(Node)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getAgent_BasePosition()
	 * @model
	 * @generated
	 */
	Node getBasePosition();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Agent#getBasePosition <em>Base Position</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Position</em>' reference.
	 * @see #getBasePosition()
	 * @generated
	 */
	void setBasePosition(Node value);

	/**
	 * Returns the value of the '<em><b>Velocity</b></em>' attribute.
	 * The default value is <code>"50.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Velocity</em>' attribute.
	 * @see #setVelocity(double)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getAgent_Velocity()
	 * @model default="50.0"
	 * @generated
	 */
	double getVelocity();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Agent#getVelocity <em>Velocity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Velocity</em>' attribute.
	 * @see #getVelocity()
	 * @generated
	 */
	void setVelocity(double value);

	/**
	 * Returns the value of the '<em><b>Scenario</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link com.company.warehouse.datamodel.Scenario#getAgents <em>Agents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario</em>' container reference.
	 * @see #setScenario(Scenario)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getAgent_Scenario()
	 * @see com.company.warehouse.datamodel.Scenario#getAgents
	 * @model opposite="agents" transient="false"
	 * @generated
	 */
	Scenario getScenario();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Agent#getScenario <em>Scenario</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario</em>' container reference.
	 * @see #getScenario()
	 * @generated
	 */
	void setScenario(Scenario value);

} // Agent
