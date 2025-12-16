package Login;

import Login.Penarikan;
import Login.Saldo;
import Login.Setoran;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-07T20:06:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Nasabah.class)
public class Nasabah_ { 

    public static volatile SingularAttribute<Nasabah, String> usernameNasabah;
    public static volatile SingularAttribute<Nasabah, String> namaIbu;
    public static volatile SingularAttribute<Nasabah, String> alamatNasabah;
    public static volatile CollectionAttribute<Nasabah, Setoran> setoranCollection;
    public static volatile SingularAttribute<Nasabah, Integer> idNasabah;
    public static volatile SingularAttribute<Nasabah, String> telepon;
    public static volatile SingularAttribute<Nasabah, String> passwordNasabah;
    public static volatile SingularAttribute<Nasabah, Saldo> saldo;
    public static volatile SingularAttribute<Nasabah, String> namaNasabah;
    public static volatile CollectionAttribute<Nasabah, Penarikan> penarikanCollection;

}