package Login;

import Login.JenisSampah;
import Login.Nasabah;
import Login.Petugas;
import Login.StokGudang;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-07T20:06:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Setoran.class)
public class Setoran_ { 

    public static volatile CollectionAttribute<Setoran, StokGudang> stokGudangCollection;
    public static volatile SingularAttribute<Setoran, JenisSampah> idJenissampah;
    public static volatile SingularAttribute<Setoran, Petugas> idPetugas;
    public static volatile SingularAttribute<Setoran, BigInteger> totalHargaSetoran;
    public static volatile SingularAttribute<Setoran, Nasabah> idNasabah;
    public static volatile SingularAttribute<Setoran, Date> tanggalSetoran;
    public static volatile SingularAttribute<Setoran, Integer> beratKgSetoran;
    public static volatile SingularAttribute<Setoran, Integer> idSetoran;
    public static volatile SingularAttribute<Setoran, String> statusSetoran;

}