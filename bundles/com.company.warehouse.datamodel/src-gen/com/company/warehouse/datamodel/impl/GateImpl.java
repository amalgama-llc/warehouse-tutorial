/**
 */
package com.company.warehouse.datamodel.impl;

import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Direction;
import com.company.warehouse.datamodel.Gate;
import com.company.warehouse.datamodel.Node;
import com.company.warehouse.datamodel.Scenario;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.company.warehouse.datamodel.impl.GateImpl#getScenario <em>Scenario</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.impl.GateImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.impl.GateImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.impl.GateImpl#getEntrance <em>Entrance</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.impl.GateImpl#getPlaces <em>Places</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GateImpl extends MinimalEObjectImpl.Container implements Gate {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final Direction DIRECTION_EDEFAULT = Direction.IN;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected Direction direction = DIRECTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEntrance() <em>Entrance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntrance()
	 * @generated
	 * @ordered
	 */
	protected Node entrance;

	/**
	 * The cached value of the '{@link #getPlaces() <em>Places</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> places;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatamodelPackage.Literals.GATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Scenario getScenario() {
		if (eContainerFeatureID() != DatamodelPackage.GATE__SCENARIO)
			return null;
		return (Scenario) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScenario(Scenario newScenario, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newScenario, DatamodelPackage.GATE__SCENARIO, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScenario(Scenario newScenario) {
		if (newScenario != eInternalContainer()
				|| (eContainerFeatureID() != DatamodelPackage.GATE__SCENARIO && newScenario != null)) {
			if (EcoreUtil.isAncestor(this, newScenario))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScenario != null)
				msgs = ((InternalEObject) newScenario).eInverseAdd(this, DatamodelPackage.SCENARIO__GATES,
						Scenario.class, msgs);
			msgs = basicSetScenario(newScenario, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.GATE__SCENARIO, newScenario,
					newScenario));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.GATE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Direction getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDirection(Direction newDirection) {
		Direction oldDirection = direction;
		direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.GATE__DIRECTION, oldDirection,
					direction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Node getEntrance() {
		if (entrance != null && entrance.eIsProxy()) {
			InternalEObject oldEntrance = (InternalEObject) entrance;
			entrance = (Node) eResolveProxy(oldEntrance);
			if (entrance != oldEntrance) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatamodelPackage.GATE__ENTRANCE,
							oldEntrance, entrance));
			}
		}
		return entrance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetEntrance() {
		return entrance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEntrance(Node newEntrance) {
		Node oldEntrance = entrance;
		entrance = newEntrance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.GATE__ENTRANCE, oldEntrance,
					entrance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Node> getPlaces() {
		if (places == null) {
			places = new EObjectResolvingEList<Node>(Node.class, this, DatamodelPackage.GATE__PLACES);
		}
		return places;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case DatamodelPackage.GATE__SCENARIO:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetScenario((Scenario) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case DatamodelPackage.GATE__SCENARIO:
			return basicSetScenario(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case DatamodelPackage.GATE__SCENARIO:
			return eInternalContainer().eInverseRemove(this, DatamodelPackage.SCENARIO__GATES, Scenario.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DatamodelPackage.GATE__SCENARIO:
			return getScenario();
		case DatamodelPackage.GATE__ID:
			return getId();
		case DatamodelPackage.GATE__DIRECTION:
			return getDirection();
		case DatamodelPackage.GATE__ENTRANCE:
			if (resolve)
				return getEntrance();
			return basicGetEntrance();
		case DatamodelPackage.GATE__PLACES:
			return getPlaces();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case DatamodelPackage.GATE__SCENARIO:
			setScenario((Scenario) newValue);
			return;
		case DatamodelPackage.GATE__ID:
			setId((String) newValue);
			return;
		case DatamodelPackage.GATE__DIRECTION:
			setDirection((Direction) newValue);
			return;
		case DatamodelPackage.GATE__ENTRANCE:
			setEntrance((Node) newValue);
			return;
		case DatamodelPackage.GATE__PLACES:
			getPlaces().clear();
			getPlaces().addAll((Collection<? extends Node>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case DatamodelPackage.GATE__SCENARIO:
			setScenario((Scenario) null);
			return;
		case DatamodelPackage.GATE__ID:
			setId(ID_EDEFAULT);
			return;
		case DatamodelPackage.GATE__DIRECTION:
			setDirection(DIRECTION_EDEFAULT);
			return;
		case DatamodelPackage.GATE__ENTRANCE:
			setEntrance((Node) null);
			return;
		case DatamodelPackage.GATE__PLACES:
			getPlaces().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case DatamodelPackage.GATE__SCENARIO:
			return getScenario() != null;
		case DatamodelPackage.GATE__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case DatamodelPackage.GATE__DIRECTION:
			return direction != DIRECTION_EDEFAULT;
		case DatamodelPackage.GATE__ENTRANCE:
			return entrance != null;
		case DatamodelPackage.GATE__PLACES:
			return places != null && !places.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", direction: ");
		result.append(direction);
		result.append(')');
		return result.toString();
	}

} //GateImpl
