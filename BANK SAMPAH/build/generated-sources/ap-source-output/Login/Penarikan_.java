package Login;

import Login.Nasabah;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-07T20:06:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Penarikan.class)
public class Penarikan_ { 

    public static volatile SingularAttribute<Penarikan, String> metode;
    public static volatile SingularAttribute<Penarikan, Integer> jumlah;
    public static volatile SingularAttribute<Penarikan, Nasabah> idNasabah;
    public static volatile SingularAttribute<Penarikan, Date> tanggal;
    public static volatile SingularAttribute<Penarikan, Integer> idPenarikan;

}