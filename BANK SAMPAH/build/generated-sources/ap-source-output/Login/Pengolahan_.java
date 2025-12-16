package Login;

import Login.JenisSampah;
import Login.PenjualanMitra;
import Login.Petugas;
import Login.StokGudang;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-07T20:06:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pengolahan.class)
public class Pengolahan_ { 

    public static volatile CollectionAttribute<Pengolahan, PenjualanMitra> penjualanMitraCollection;
    public static volatile CollectionAttribute<Pengolahan, StokGudang> stokGudangCollection;
    public static volatile SingularAttribute<Pengolahan, JenisSampah> idJenissampah;
    public static volatile SingularAttribute<Pengolahan, Petugas> idPetugas;
    public static volatile SingularAttribute<Pengolahan, String> beratHasilPengolahan;
    public static volatile SingularAttribute<Pengolahan, Integer> idPengolahan;
    public static volatile SingularAttribute<Pengolahan, Date> tanggalPengolahan;
    public static volatile SingularAttribute<Pengolahan, String> metodePengolahan;
    public static volatile SingularAttribute<Pengolahan, String> hasilPengolahan;

}