/**
 */
package com.company.warehouse.datamodel.impl;

import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Forklift;
import com.company.warehouse.datamodel.Node;
import com.company.warehouse.datamodel.Scenario;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Forklift</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.company.warehouse.datamodel.impl.ForkliftImpl#getScenario <em>Scenario</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.impl.ForkliftImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.impl.ForkliftImpl#getBase <em>Base</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.impl.ForkliftImpl#getVelocity <em>Velocity</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.impl.ForkliftImpl#getLoadingTimeSec <em>Loading Time Sec</em>}</li>
 *   <li>{@link com.company.warehouse.datamodel.impl.ForkliftImpl#getUnloadingTimeSec <em>Unloading Time Sec</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ForkliftImpl extends MinimalEObjectImpl.Container implements Forklift {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected Node base;

	/**
	 * The default value of the '{@link #getVelocity() <em>Velocity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVelocity()
	 * @generated
	 * @ordered
	 */
	protected static final double VELOCITY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getVelocity() <em>Velocity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVelocity()
	 * @generated
	 * @ordered
	 */
	protected double velocity = VELOCITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getLoadingTimeSec() <em>Loading Time Sec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadingTimeSec()
	 * @generated
	 * @ordered
	 */
	protected static final double LOADING_TIME_SEC_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLoadingTimeSec() <em>Loading Time Sec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadingTimeSec()
	 * @generated
	 * @ordered
	 */
	protected double loadingTimeSec = LOADING_TIME_SEC_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnloadingTimeSec() <em>Unloading Time Sec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnloadingTimeSec()
	 * @generated
	 * @ordered
	 */
	protected static final double UNLOADING_TIME_SEC_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getUnloadingTimeSec() <em>Unloading Time Sec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnloadingTimeSec()
	 * @generated
	 * @ordered
	 */
	protected double unloadingTimeSec = UNLOADING_TIME_SEC_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForkliftImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatamodelPackage.Literals.FORKLIFT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Scenario getScenario() {
		if (eContainerFeatureID() != DatamodelPackage.FORKLIFT__SCENARIO)
			return null;
		return (Scenario) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScenario(Scenario newScenario, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newScenario, DatamodelPackage.FORKLIFT__SCENARIO, msgs);
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
				|| (eContainerFeatureID() != DatamodelPackage.FORKLIFT__SCENARIO && newScenario != null)) {
			if (EcoreUtil.isAncestor(this, newScenario))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScenario != null)
				msgs = ((InternalEObject) newScenario).eInverseAdd(this, DatamodelPackage.SCENARIO__FORKLIFTS,
						Scenario.class, msgs);
			msgs = basicSetScenario(newScenario, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.FORKLIFT__SCENARIO, newScenario,
					newScenario));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.FORKLIFT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Node getBase() {
		if (base != null && base.eIsProxy()) {
			InternalEObject oldBase = (InternalEObject) base;
			base = (Node) eResolveProxy(oldBase);
			if (base != oldBase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatamodelPackage.FORKLIFT__BASE, oldBase,
							base));
			}
		}
		return base;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetBase() {
		return base;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBase(Node newBase) {
		Node oldBase = base;
		base = newBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.FORKLIFT__BASE, oldBase, base));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getVelocity() {
		return velocity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVelocity(double newVelocity) {
		double oldVelocity = velocity;
		velocity = newVelocity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.FORKLIFT__VELOCITY, oldVelocity,
					velocity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getLoadingTimeSec() {
		return loadingTimeSec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLoadingTimeSec(double newLoadingTimeSec) {
		double oldLoadingTimeSec = loadingTimeSec;
		loadingTimeSec = newLoadingTimeSec;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.FORKLIFT__LOADING_TIME_SEC,
					oldLoadingTimeSec, loadingTimeSec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getUnloadingTimeSec() {
		return unloadingTimeSec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnloadingTimeSec(double newUnloadingTimeSec) {
		double oldUnloadingTimeSec = unloadingTimeSec;
		unloadingTimeSec = newUnloadingTimeSec;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.FORKLIFT__UNLOADING_TIME_SEC,
					oldUnloadingTimeSec, unloadingTimeSec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case DatamodelPackage.FORKLIFT__SCENARIO:
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
		case DatamodelPackage.FORKLIFT__SCENARIO:
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
		case DatamodelPackage.FORKLIFT__SCENARIO:
			return eInternalContainer().eInverseRemove(this, DatamodelPackage.SCENARIO__FORKLIFTS, Scenario.class,
					msgs);
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
		case DatamodelPackage.FORKLIFT__SCENARIO:
			return getScenario();
		case DatamodelPackage.FORKLIFT__NAME:
			return getName();
		case DatamodelPackage.FORKLIFT__BASE:
			if (resolve)
				return getBase();
			return basicGetBase();
		case DatamodelPackage.FORKLIFT__VELOCITY:
			return getVelocity();
		case DatamodelPackage.FORKLIFT__LOADING_TIME_SEC:
			return getLoadingTimeSec();
		case DatamodelPackage.FORKLIFT__UNLOADING_TIME_SEC:
			return getUnloadingTimeSec();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case DatamodelPackage.FORKLIFT__SCENARIO:
			setScenario((Scenario) newValue);
			return;
		case DatamodelPackage.FORKLIFT__NAME:
			setName((String) newValue);
			return;
		case DatamodelPackage.FORKLIFT__BASE:
			setBase((Node) newValue);
			return;
		case DatamodelPackage.FORKLIFT__VELOCITY:
			setVelocity((Double) newValue);
			return;
		case DatamodelPackage.FORKLIFT__LOADING_TIME_SEC:
			setLoadingTimeSec((Double) newValue);
			return;
		case DatamodelPackage.FORKLIFT__UNLOADING_TIME_SEC:
			setUnloadingTimeSec((Double) newValue);
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
		case DatamodelPackage.FORKLIFT__SCENARIO:
			setScenario((Scenario) null);
			return;
		case DatamodelPackage.FORKLIFT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case DatamodelPackage.FORKLIFT__BASE:
			setBase((Node) null);
			return;
		case DatamodelPackage.FORKLIFT__VELOCITY:
			setVelocity(VELOCITY_EDEFAULT);
			return;
		case DatamodelPackage.FORKLIFT__LOADING_TIME_SEC:
			setLoadingTimeSec(LOADING_TIME_SEC_EDEFAULT);
			return;
		case DatamodelPackage.FORKLIFT__UNLOADING_TIME_SEC:
			setUnloadingTimeSec(UNLOADING_TIME_SEC_EDEFAULT);
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
		case DatamodelPackage.FORKLIFT__SCENARIO:
			return getScenario() != null;
		case DatamodelPackage.FORKLIFT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case DatamodelPackage.FORKLIFT__BASE:
			return base != null;
		case DatamodelPackage.FORKLIFT__VELOCITY:
			return velocity != VELOCITY_EDEFAULT;
		case DatamodelPackage.FORKLIFT__LOADING_TIME_SEC:
			return loadingTimeSec != LOADING_TIME_SEC_EDEFAULT;
		case DatamodelPackage.FORKLIFT__UNLOADING_TIME_SEC:
			return unloadingTimeSec != UNLOADING_TIME_SEC_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", velocity: ");
		result.append(velocity);
		result.append(", loadingTimeSec: ");
		result.append(loadingTimeSec);
		result.append(", unloadingTimeSec: ");
		result.append(unloadingTimeSec);
		result.append(')');
		return result.toString();
	}

} //ForkliftImpl
