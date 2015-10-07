/**
 */
package ca.mcgill.sel.ram.impl;

import ca.mcgill.sel.core.impl.CORENamedElementImpl;
import ca.mcgill.sel.ram.NamedElement;
import ca.mcgill.sel.ram.RamPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class NamedElementImpl extends CORENamedElementImpl implements NamedElement {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NamedElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.NAMED_ELEMENT;
    }

} //NamedElementImpl
