package Login;

import Login.JenisSampah;
import Login.Pengolahan;
import Login.Setoran;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-07T20:06:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(StokGudang.class)
public class StokGudang_ { 

    public static volatile SingularAttribute<StokGudang, Date> tanggalMasuk;
    public static volatile SingularAttribute<StokGudang, Integer> jumlah;
    public static volatile SingularAttribute<StokGudang, JenisSampah> idJenissampah;
    public static volatile SingularAttribute<StokGudang, String> statusStok;
    public static volatile SingularAttribute<StokGudang, Pengolahan> idPengolahan;
    public static volatile SingularAttribute<StokGudang, Integer> beratTersedia;
    public static volatile SingularAttribute<StokGudang, Setoran> idSetoran;
    public static volatile SingularAttribute<StokGudang, Integer> idStok;
    public static volatile SingularAttribute<StokGudang, Integer> beratMasuk;

}