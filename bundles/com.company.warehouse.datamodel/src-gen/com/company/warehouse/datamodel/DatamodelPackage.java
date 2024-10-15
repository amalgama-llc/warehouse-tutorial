/**
 */
package com.company.warehouse.datamodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.company.warehouse.datamodel.DatamodelFactory
 * @model kind="package"
 * @generated
 */
public interface DatamodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "datamodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/datamodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "datamodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DatamodelPackage eINSTANCE = com.company.warehouse.datamodel.impl.DatamodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.company.warehouse.datamodel.impl.ScenarioImpl <em>Scenario</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.company.warehouse.datamodel.impl.ScenarioImpl
	 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getScenario()
	 * @generated
	 */
	int SCENARIO = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__NAME = 0;

	/**
	 * The feature id for the '<em><b>Begin Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__BEGIN_DATE = 1;

	/**
	 * The feature id for the '<em><b>End Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__END_DATE = 2;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__NODES = 3;

	/**
	 * The feature id for the '<em><b>Arcs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__ARCS = 4;

	/**
	 * The feature id for the '<em><b>Agents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__AGENTS = 5;

	/**
	 * The feature id for the '<em><b>Truck Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__TRUCK_CAPACITY = 6;

	/**
	 * The feature id for the '<em><b>Truck Arrival Interval Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__TRUCK_ARRIVAL_INTERVAL_MIN = 7;

	/**
	 * The feature id for the '<em><b>Forklifts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__FORKLIFTS = 8;

	/**
	 * The feature id for the '<em><b>Storage Places</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__STORAGE_PLACES = 9;

	/**
	 * The feature id for the '<em><b>Gates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__GATES = 10;

	/**
	 * The number of structural features of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FEATURE_COUNT = 11;

	/**
	 * The number of operations of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.company.warehouse.datamodel.impl.ArcImpl <em>Arc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.company.warehouse.datamodel.impl.ArcImpl
	 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getArc()
	 * @generated
	 */
	int ARC = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__NAME = 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__SOURCE = 2;

	/**
	 * The feature id for the '<em><b>Dest</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__DEST = 3;

	/**
	 * The feature id for the '<em><b>Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__POINTS = 4;

	/**
	 * The feature id for the '<em><b>Scenario</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__SCENARIO = 5;

	/**
	 * The number of structural features of the '<em>Arc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Arc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.company.warehouse.datamodel.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.company.warehouse.datamodel.impl.NodeImpl
	 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__Y = 2;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__X = 3;

	/**
	 * The feature id for the '<em><b>Scenario</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__SCENARIO = 4;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.company.warehouse.datamodel.impl.PointImpl <em>Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.company.warehouse.datamodel.impl.PointImpl
	 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getPoint()
	 * @generated
	 */
	int POINT = 3;

	/**
	 * The feature id for the '<em><b>Arc</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__ARC = 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__Y = 1;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__X = 2;

	/**
	 * The number of structural features of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.company.warehouse.datamodel.impl.AgentImpl <em>Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.company.warehouse.datamodel.impl.AgentImpl
	 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getAgent()
	 * @generated
	 */
	int AGENT = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Included</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__INCLUDED = 2;

	/**
	 * The feature id for the '<em><b>Base Position</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__BASE_POSITION = 3;

	/**
	 * The feature id for the '<em><b>Velocity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__VELOCITY = 4;

	/**
	 * The feature id for the '<em><b>Scenario</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__SCENARIO = 5;

	/**
	 * The number of structural features of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.company.warehouse.datamodel.impl.ForkliftImpl <em>Forklift</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.company.warehouse.datamodel.impl.ForkliftImpl
	 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getForklift()
	 * @generated
	 */
	int FORKLIFT = 5;

	/**
	 * The feature id for the '<em><b>Scenario</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORKLIFT__SCENARIO = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORKLIFT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Base</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORKLIFT__BASE = 2;

	/**
	 * The feature id for the '<em><b>Velocity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORKLIFT__VELOCITY = 3;

	/**
	 * The feature id for the '<em><b>Loading Time Sec</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORKLIFT__LOADING_TIME_SEC = 4;

	/**
	 * The feature id for the '<em><b>Unloading Time Sec</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORKLIFT__UNLOADING_TIME_SEC = 5;

	/**
	 * The number of structural features of the '<em>Forklift</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORKLIFT_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Forklift</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORKLIFT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.company.warehouse.datamodel.impl.GateImpl <em>Gate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.company.warehouse.datamodel.impl.GateImpl
	 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getGate()
	 * @generated
	 */
	int GATE = 6;

	/**
	 * The feature id for the '<em><b>Scenario</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__SCENARIO = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ID = 1;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__DIRECTION = 2;

	/**
	 * The feature id for the '<em><b>Entrance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ENTRANCE = 3;

	/**
	 * The feature id for the '<em><b>Places</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__PLACES = 4;

	/**
	 * The number of structural features of the '<em>Gate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Gate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.company.warehouse.datamodel.Direction <em>Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.company.warehouse.datamodel.Direction
	 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getDirection()
	 * @generated
	 */
	int DIRECTION = 7;

	/**
	 * Returns the meta object for class '{@link com.company.warehouse.datamodel.Scenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scenario</em>'.
	 * @see com.company.warehouse.datamodel.Scenario
	 * @generated
	 */
	EClass getScenario();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Scenario#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getName()
	 * @see #getScenario()
	 * @generated
	 */
	EAttribute getScenario_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Scenario#getBeginDate <em>Begin Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Begin Date</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getBeginDate()
	 * @see #getScenario()
	 * @generated
	 */
	EAttribute getScenario_BeginDate();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Scenario#getEndDate <em>End Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Date</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getEndDate()
	 * @see #getScenario()
	 * @generated
	 */
	EAttribute getScenario_EndDate();

	/**
	 * Returns the meta object for the containment reference list '{@link com.company.warehouse.datamodel.Scenario#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getNodes()
	 * @see #getScenario()
	 * @generated
	 */
	EReference getScenario_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.company.warehouse.datamodel.Scenario#getArcs <em>Arcs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arcs</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getArcs()
	 * @see #getScenario()
	 * @generated
	 */
	EReference getScenario_Arcs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.company.warehouse.datamodel.Scenario#getAgents <em>Agents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Agents</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getAgents()
	 * @see #getScenario()
	 * @generated
	 */
	EReference getScenario_Agents();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Scenario#getTruckCapacity <em>Truck Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Truck Capacity</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getTruckCapacity()
	 * @see #getScenario()
	 * @generated
	 */
	EAttribute getScenario_TruckCapacity();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Scenario#getTruckArrivalIntervalMin <em>Truck Arrival Interval Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Truck Arrival Interval Min</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getTruckArrivalIntervalMin()
	 * @see #getScenario()
	 * @generated
	 */
	EAttribute getScenario_TruckArrivalIntervalMin();

	/**
	 * Returns the meta object for the containment reference list '{@link com.company.warehouse.datamodel.Scenario#getForklifts <em>Forklifts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Forklifts</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getForklifts()
	 * @see #getScenario()
	 * @generated
	 */
	EReference getScenario_Forklifts();

	/**
	 * Returns the meta object for the reference list '{@link com.company.warehouse.datamodel.Scenario#getStoragePlaces <em>Storage Places</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Storage Places</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getStoragePlaces()
	 * @see #getScenario()
	 * @generated
	 */
	EReference getScenario_StoragePlaces();

	/**
	 * Returns the meta object for the containment reference list '{@link com.company.warehouse.datamodel.Scenario#getGates <em>Gates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Gates</em>'.
	 * @see com.company.warehouse.datamodel.Scenario#getGates()
	 * @see #getScenario()
	 * @generated
	 */
	EReference getScenario_Gates();

	/**
	 * Returns the meta object for class '{@link com.company.warehouse.datamodel.Arc <em>Arc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arc</em>'.
	 * @see com.company.warehouse.datamodel.Arc
	 * @generated
	 */
	EClass getArc();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Arc#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.company.warehouse.datamodel.Arc#getId()
	 * @see #getArc()
	 * @generated
	 */
	EAttribute getArc_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Arc#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.company.warehouse.datamodel.Arc#getName()
	 * @see #getArc()
	 * @generated
	 */
	EAttribute getArc_Name();

	/**
	 * Returns the meta object for the reference '{@link com.company.warehouse.datamodel.Arc#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see com.company.warehouse.datamodel.Arc#getSource()
	 * @see #getArc()
	 * @generated
	 */
	EReference getArc_Source();

	/**
	 * Returns the meta object for the reference '{@link com.company.warehouse.datamodel.Arc#getDest <em>Dest</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dest</em>'.
	 * @see com.company.warehouse.datamodel.Arc#getDest()
	 * @see #getArc()
	 * @generated
	 */
	EReference getArc_Dest();

	/**
	 * Returns the meta object for the containment reference list '{@link com.company.warehouse.datamodel.Arc#getPoints <em>Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Points</em>'.
	 * @see com.company.warehouse.datamodel.Arc#getPoints()
	 * @see #getArc()
	 * @generated
	 */
	EReference getArc_Points();

	/**
	 * Returns the meta object for the container reference '{@link com.company.warehouse.datamodel.Arc#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Scenario</em>'.
	 * @see com.company.warehouse.datamodel.Arc#getScenario()
	 * @see #getArc()
	 * @generated
	 */
	EReference getArc_Scenario();

	/**
	 * Returns the meta object for class '{@link com.company.warehouse.datamodel.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see com.company.warehouse.datamodel.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Node#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.company.warehouse.datamodel.Node#getId()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Node#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.company.warehouse.datamodel.Node#getName()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Node#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see com.company.warehouse.datamodel.Node#getY()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Y();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Node#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see com.company.warehouse.datamodel.Node#getX()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_X();

	/**
	 * Returns the meta object for the container reference '{@link com.company.warehouse.datamodel.Node#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Scenario</em>'.
	 * @see com.company.warehouse.datamodel.Node#getScenario()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Scenario();

	/**
	 * Returns the meta object for class '{@link com.company.warehouse.datamodel.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Point</em>'.
	 * @see com.company.warehouse.datamodel.Point
	 * @generated
	 */
	EClass getPoint();

	/**
	 * Returns the meta object for the container reference '{@link com.company.warehouse.datamodel.Point#getArc <em>Arc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Arc</em>'.
	 * @see com.company.warehouse.datamodel.Point#getArc()
	 * @see #getPoint()
	 * @generated
	 */
	EReference getPoint_Arc();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Point#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see com.company.warehouse.datamodel.Point#getY()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_Y();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Point#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see com.company.warehouse.datamodel.Point#getX()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_X();

	/**
	 * Returns the meta object for class '{@link com.company.warehouse.datamodel.Agent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent</em>'.
	 * @see com.company.warehouse.datamodel.Agent
	 * @generated
	 */
	EClass getAgent();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Agent#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.company.warehouse.datamodel.Agent#getId()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Agent#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.company.warehouse.datamodel.Agent#getName()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Agent#isIncluded <em>Included</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Included</em>'.
	 * @see com.company.warehouse.datamodel.Agent#isIncluded()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_Included();

	/**
	 * Returns the meta object for the reference '{@link com.company.warehouse.datamodel.Agent#getBasePosition <em>Base Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Position</em>'.
	 * @see com.company.warehouse.datamodel.Agent#getBasePosition()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_BasePosition();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Agent#getVelocity <em>Velocity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Velocity</em>'.
	 * @see com.company.warehouse.datamodel.Agent#getVelocity()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_Velocity();

	/**
	 * Returns the meta object for the container reference '{@link com.company.warehouse.datamodel.Agent#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Scenario</em>'.
	 * @see com.company.warehouse.datamodel.Agent#getScenario()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_Scenario();

	/**
	 * Returns the meta object for class '{@link com.company.warehouse.datamodel.Forklift <em>Forklift</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Forklift</em>'.
	 * @see com.company.warehouse.datamodel.Forklift
	 * @generated
	 */
	EClass getForklift();

	/**
	 * Returns the meta object for the container reference '{@link com.company.warehouse.datamodel.Forklift#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Scenario</em>'.
	 * @see com.company.warehouse.datamodel.Forklift#getScenario()
	 * @see #getForklift()
	 * @generated
	 */
	EReference getForklift_Scenario();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Forklift#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.company.warehouse.datamodel.Forklift#getName()
	 * @see #getForklift()
	 * @generated
	 */
	EAttribute getForklift_Name();

	/**
	 * Returns the meta object for the reference '{@link com.company.warehouse.datamodel.Forklift#getBase <em>Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base</em>'.
	 * @see com.company.warehouse.datamodel.Forklift#getBase()
	 * @see #getForklift()
	 * @generated
	 */
	EReference getForklift_Base();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Forklift#getVelocity <em>Velocity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Velocity</em>'.
	 * @see com.company.warehouse.datamodel.Forklift#getVelocity()
	 * @see #getForklift()
	 * @generated
	 */
	EAttribute getForklift_Velocity();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Forklift#getLoadingTimeSec <em>Loading Time Sec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loading Time Sec</em>'.
	 * @see com.company.warehouse.datamodel.Forklift#getLoadingTimeSec()
	 * @see #getForklift()
	 * @generated
	 */
	EAttribute getForklift_LoadingTimeSec();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Forklift#getUnloadingTimeSec <em>Unloading Time Sec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unloading Time Sec</em>'.
	 * @see com.company.warehouse.datamodel.Forklift#getUnloadingTimeSec()
	 * @see #getForklift()
	 * @generated
	 */
	EAttribute getForklift_UnloadingTimeSec();

	/**
	 * Returns the meta object for class '{@link com.company.warehouse.datamodel.Gate <em>Gate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gate</em>'.
	 * @see com.company.warehouse.datamodel.Gate
	 * @generated
	 */
	EClass getGate();

	/**
	 * Returns the meta object for the container reference '{@link com.company.warehouse.datamodel.Gate#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Scenario</em>'.
	 * @see com.company.warehouse.datamodel.Gate#getScenario()
	 * @see #getGate()
	 * @generated
	 */
	EReference getGate_Scenario();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Gate#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.company.warehouse.datamodel.Gate#getId()
	 * @see #getGate()
	 * @generated
	 */
	EAttribute getGate_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.company.warehouse.datamodel.Gate#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see com.company.warehouse.datamodel.Gate#getDirection()
	 * @see #getGate()
	 * @generated
	 */
	EAttribute getGate_Direction();

	/**
	 * Returns the meta object for the reference '{@link com.company.warehouse.datamodel.Gate#getEntrance <em>Entrance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entrance</em>'.
	 * @see com.company.warehouse.datamodel.Gate#getEntrance()
	 * @see #getGate()
	 * @generated
	 */
	EReference getGate_Entrance();

	/**
	 * Returns the meta object for the reference list '{@link com.company.warehouse.datamodel.Gate#getPlaces <em>Places</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Places</em>'.
	 * @see com.company.warehouse.datamodel.Gate#getPlaces()
	 * @see #getGate()
	 * @generated
	 */
	EReference getGate_Places();

	/**
	 * Returns the meta object for enum '{@link com.company.warehouse.datamodel.Direction <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Direction</em>'.
	 * @see com.company.warehouse.datamodel.Direction
	 * @generated
	 */
	EEnum getDirection();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DatamodelFactory getDatamodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.company.warehouse.datamodel.impl.ScenarioImpl <em>Scenario</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.company.warehouse.datamodel.impl.ScenarioImpl
		 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getScenario()
		 * @generated
		 */
		EClass SCENARIO = eINSTANCE.getScenario();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO__NAME = eINSTANCE.getScenario_Name();

		/**
		 * The meta object literal for the '<em><b>Begin Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO__BEGIN_DATE = eINSTANCE.getScenario_BeginDate();

		/**
		 * The meta object literal for the '<em><b>End Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO__END_DATE = eINSTANCE.getScenario_EndDate();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO__NODES = eINSTANCE.getScenario_Nodes();

		/**
		 * The meta object literal for the '<em><b>Arcs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO__ARCS = eINSTANCE.getScenario_Arcs();

		/**
		 * The meta object literal for the '<em><b>Agents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO__AGENTS = eINSTANCE.getScenario_Agents();

		/**
		 * The meta object literal for the '<em><b>Truck Capacity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO__TRUCK_CAPACITY = eINSTANCE.getScenario_TruckCapacity();

		/**
		 * The meta object literal for the '<em><b>Truck Arrival Interval Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO__TRUCK_ARRIVAL_INTERVAL_MIN = eINSTANCE.getScenario_TruckArrivalIntervalMin();

		/**
		 * The meta object literal for the '<em><b>Forklifts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO__FORKLIFTS = eINSTANCE.getScenario_Forklifts();

		/**
		 * The meta object literal for the '<em><b>Storage Places</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO__STORAGE_PLACES = eINSTANCE.getScenario_StoragePlaces();

		/**
		 * The meta object literal for the '<em><b>Gates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO__GATES = eINSTANCE.getScenario_Gates();

		/**
		 * The meta object literal for the '{@link com.company.warehouse.datamodel.impl.ArcImpl <em>Arc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.company.warehouse.datamodel.impl.ArcImpl
		 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getArc()
		 * @generated
		 */
		EClass ARC = eINSTANCE.getArc();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARC__ID = eINSTANCE.getArc_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARC__NAME = eINSTANCE.getArc_Name();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARC__SOURCE = eINSTANCE.getArc_Source();

		/**
		 * The meta object literal for the '<em><b>Dest</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARC__DEST = eINSTANCE.getArc_Dest();

		/**
		 * The meta object literal for the '<em><b>Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARC__POINTS = eINSTANCE.getArc_Points();

		/**
		 * The meta object literal for the '<em><b>Scenario</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARC__SCENARIO = eINSTANCE.getArc_Scenario();

		/**
		 * The meta object literal for the '{@link com.company.warehouse.datamodel.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.company.warehouse.datamodel.impl.NodeImpl
		 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__ID = eINSTANCE.getNode_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__NAME = eINSTANCE.getNode_Name();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__Y = eINSTANCE.getNode_Y();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__X = eINSTANCE.getNode_X();

		/**
		 * The meta object literal for the '<em><b>Scenario</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__SCENARIO = eINSTANCE.getNode_Scenario();

		/**
		 * The meta object literal for the '{@link com.company.warehouse.datamodel.impl.PointImpl <em>Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.company.warehouse.datamodel.impl.PointImpl
		 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getPoint()
		 * @generated
		 */
		EClass POINT = eINSTANCE.getPoint();

		/**
		 * The meta object literal for the '<em><b>Arc</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POINT__ARC = eINSTANCE.getPoint_Arc();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT__Y = eINSTANCE.getPoint_Y();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT__X = eINSTANCE.getPoint_X();

		/**
		 * The meta object literal for the '{@link com.company.warehouse.datamodel.impl.AgentImpl <em>Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.company.warehouse.datamodel.impl.AgentImpl
		 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getAgent()
		 * @generated
		 */
		EClass AGENT = eINSTANCE.getAgent();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT__ID = eINSTANCE.getAgent_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT__NAME = eINSTANCE.getAgent_Name();

		/**
		 * The meta object literal for the '<em><b>Included</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT__INCLUDED = eINSTANCE.getAgent_Included();

		/**
		 * The meta object literal for the '<em><b>Base Position</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__BASE_POSITION = eINSTANCE.getAgent_BasePosition();

		/**
		 * The meta object literal for the '<em><b>Velocity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT__VELOCITY = eINSTANCE.getAgent_Velocity();

		/**
		 * The meta object literal for the '<em><b>Scenario</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__SCENARIO = eINSTANCE.getAgent_Scenario();

		/**
		 * The meta object literal for the '{@link com.company.warehouse.datamodel.impl.ForkliftImpl <em>Forklift</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.company.warehouse.datamodel.impl.ForkliftImpl
		 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getForklift()
		 * @generated
		 */
		EClass FORKLIFT = eINSTANCE.getForklift();

		/**
		 * The meta object literal for the '<em><b>Scenario</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORKLIFT__SCENARIO = eINSTANCE.getForklift_Scenario();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORKLIFT__NAME = eINSTANCE.getForklift_Name();

		/**
		 * The meta object literal for the '<em><b>Base</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORKLIFT__BASE = eINSTANCE.getForklift_Base();

		/**
		 * The meta object literal for the '<em><b>Velocity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORKLIFT__VELOCITY = eINSTANCE.getForklift_Velocity();

		/**
		 * The meta object literal for the '<em><b>Loading Time Sec</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORKLIFT__LOADING_TIME_SEC = eINSTANCE.getForklift_LoadingTimeSec();

		/**
		 * The meta object literal for the '<em><b>Unloading Time Sec</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORKLIFT__UNLOADING_TIME_SEC = eINSTANCE.getForklift_UnloadingTimeSec();

		/**
		 * The meta object literal for the '{@link com.company.warehouse.datamodel.impl.GateImpl <em>Gate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.company.warehouse.datamodel.impl.GateImpl
		 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getGate()
		 * @generated
		 */
		EClass GATE = eINSTANCE.getGate();

		/**
		 * The meta object literal for the '<em><b>Scenario</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GATE__SCENARIO = eINSTANCE.getGate_Scenario();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GATE__ID = eINSTANCE.getGate_Id();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GATE__DIRECTION = eINSTANCE.getGate_Direction();

		/**
		 * The meta object literal for the '<em><b>Entrance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GATE__ENTRANCE = eINSTANCE.getGate_Entrance();

		/**
		 * The meta object literal for the '<em><b>Places</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GATE__PLACES = eINSTANCE.getGate_Places();

		/**
		 * The meta object literal for the '{@link com.company.warehouse.datamodel.Direction <em>Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.company.warehouse.datamodel.Direction
		 * @see com.company.warehouse.datamodel.impl.DatamodelPackageImpl#getDirection()
		 * @generated
		 */
		EEnum DIRECTION = eINSTANCE.getDirection();

	}

} //DatamodelPackage
