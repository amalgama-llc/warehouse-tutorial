/**
 */
package com.company.warehouse.datamodel;

import java.time.LocalDateTime;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.company.warehouse.datamodel.Scenario#getName <em>Name</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Scenario#getBeginDate <em>Begin Date</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Scenario#getEndDate <em>End Date</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Scenario#getNodes <em>Nodes</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Scenario#getArcs <em>Arcs</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Scenario#getAgents <em>Agents</em>}</li>
 * </ul>
 *
 * @see com.company.warehouse.datamodel.DatamodelPackage#getScenario()
 * @model
 * @generated
 */
public interface Scenario extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getScenario_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Scenario#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Begin Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Begin Date</em>' attribute.
	 * @see #setBeginDate(LocalDateTime)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getScenario_BeginDate()
	 * @model dataType="com.amalgamasimulation.ecoreutils.LocalDateTime"
	 * @generated
	 */
	LocalDateTime getBeginDate();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Scenario#getBeginDate <em>Begin Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Begin Date</em>' attribute.
	 * @see #getBeginDate()
	 * @generated
	 */
	void setBeginDate(LocalDateTime value);

	/**
	 * Returns the value of the '<em><b>End Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Date</em>' attribute.
	 * @see #setEndDate(LocalDateTime)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getScenario_EndDate()
	 * @model dataType="com.amalgamasimulation.ecoreutils.LocalDateTime"
	 * @generated
	 */
	LocalDateTime getEndDate();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Scenario#getEndDate <em>End Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Date</em>' attribute.
	 * @see #getEndDate()
	 * @generated
	 */
	void setEndDate(LocalDateTime value);

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link com.company.warehouse.datamodel.Node}.
	 * It is bidirectional and its opposite is '{@link com.company.warehouse.datamodel.Node#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getScenario_Nodes()
	 * @see com.company.warehouse.datamodel.Node#getScenario
	 * @model opposite="scenario" containment="true"
	 * @generated
	 */
	EList<Node> getNodes();

	/**
	 * Returns the value of the '<em><b>Arcs</b></em>' containment reference list.
	 * The list contents are of type {@link com.company.warehouse.datamodel.Arc}.
	 * It is bidirectional and its opposite is '{@link com.company.warehouse.datamodel.Arc#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arcs</em>' containment reference list.
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getScenario_Arcs()
	 * @see com.company.warehouse.datamodel.Arc#getScenario
	 * @model opposite="scenario" containment="true"
	 * @generated
	 */
	EList<Arc> getArcs();

	/**
	 * Returns the value of the '<em><b>Agents</b></em>' containment reference list.
	 * The list contents are of type {@link com.company.warehouse.datamodel.Agent}.
	 * It is bidirectional and its opposite is '{@link com.company.warehouse.datamodel.Agent#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agents</em>' containment reference list.
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getScenario_Agents()
	 * @see com.company.warehouse.datamodel.Agent#getScenario
	 * @model opposite="scenario" containment="true"
	 * @generated
	 */
	EList<Agent> getAgents();

} // Scenario

