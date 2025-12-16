package Login;

import Login.Nasabah;
import java.math.BigInteger;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-07T20:06:12", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Saldo.class)
public class Saldo_ { 

    public static volatile SingularAttribute<Saldo, BigInteger> totalSaldo;
    public static volatile SingularAttribute<Saldo, Nasabah> idNasabah;
    public static volatile SingularAttribute<Saldo, Integer> idSaldo;

}