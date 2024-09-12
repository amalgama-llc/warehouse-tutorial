/**
 */
package com.company.warehouse.datamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Forklift</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.company.warehouse.datamodel.Forklift#getScenario <em>Scenario</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Forklift#getName <em>Name</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Forklift#getBase <em>Base</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Forklift#getVelocity <em>Velocity</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Forklift#getLoadingTimeSec <em>Loading Time Sec</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.Forklift#getUnloadingTimeSec <em>Unloading Time Sec</em>}</li>
 * </ul>
 *
 * @see com.company.warehouse.datamodel.DatamodelPackage#getForklift()
 * @model
 * @generated
 */
public interface Forklift extends EObject {
	/**
	 * Returns the value of the '<em><b>Scenario</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link com.company.warehouse.datamodel.Scenario#getForklifts <em>Forklifts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario</em>' container reference.
	 * @see #setScenario(Scenario)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getForklift_Scenario()
	 * @see com.company.warehouse.datamodel.Scenario#getForklifts
	 * @model opposite="forklifts" transient="false"
	 * @generated
	 */
	Scenario getScenario();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Forklift#getScenario <em>Scenario</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario</em>' container reference.
	 * @see #getScenario()
	 * @generated
	 */
	void setScenario(Scenario value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getForklift_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Forklift#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Base</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base</em>' reference.
	 * @see #setBase(Node)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getForklift_Base()
	 * @model
	 * @generated
	 */
	Node getBase();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Forklift#getBase <em>Base</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base</em>' reference.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(Node value);

	/**
	 * Returns the value of the '<em><b>Velocity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Velocity</em>' attribute.
	 * @see #setVelocity(double)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getForklift_Velocity()
	 * @model
	 * @generated
	 */
	double getVelocity();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Forklift#getVelocity <em>Velocity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Velocity</em>' attribute.
	 * @see #getVelocity()
	 * @generated
	 */
	void setVelocity(double value);

	/**
	 * Returns the value of the '<em><b>Loading Time Sec</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loading Time Sec</em>' attribute.
	 * @see #setLoadingTimeSec(double)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getForklift_LoadingTimeSec()
	 * @model
	 * @generated
	 */
	double getLoadingTimeSec();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Forklift#getLoadingTimeSec <em>Loading Time Sec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loading Time Sec</em>' attribute.
	 * @see #getLoadingTimeSec()
	 * @generated
	 */
	void setLoadingTimeSec(double value);

	/**
	 * Returns the value of the '<em><b>Unloading Time Sec</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unloading Time Sec</em>' attribute.
	 * @see #setUnloadingTimeSec(double)
	 * @see com.company.warehouse.datamodel.DatamodelPackage#getForklift_UnloadingTimeSec()
	 * @model
	 * @generated
	 */
	double getUnloadingTimeSec();

	/**
	 * Sets the value of the '{@link com.company.warehouse.datamodel.Forklift#getUnloadingTimeSec <em>Unloading Time Sec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unloading Time Sec</em>' attribute.
	 * @see #getUnloadingTimeSec()
	 * @generated
	 */
	void setUnloadingTimeSec(double value);

} // Forklift
