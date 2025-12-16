package Login;

import Login.Pengolahan;
import Login.Setoran;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-07T20:06:12", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Petugas.class)
public class Petugas_ { 

    public static volatile CollectionAttribute<Petugas, Pengolahan> pengolahanCollection;
    public static volatile SingularAttribute<Petugas, String> usernamePetugas;
    public static volatile SingularAttribute<Petugas, Integer> idPetugas;
    public static volatile SingularAttribute<Petugas, String> passwordPetugas;
    public static volatile SingularAttribute<Petugas, String> namaIbu;
    public static volatile CollectionAttribute<Petugas, Setoran> setoranCollection;
    public static volatile SingularAttribute<Petugas, String> namaPetugas;

}