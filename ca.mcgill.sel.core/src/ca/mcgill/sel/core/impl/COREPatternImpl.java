/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREPattern;
import ca.mcgill.sel.core.CorePackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class COREPatternImpl<S extends COREModel> extends COREModelCompositionSpecificationImpl<S> implements COREPattern<S> {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREPatternImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_PATTERN;
    }

} //COREPatternImpl
