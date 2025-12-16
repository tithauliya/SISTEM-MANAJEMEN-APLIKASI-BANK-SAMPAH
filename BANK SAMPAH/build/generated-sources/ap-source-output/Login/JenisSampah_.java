package Login;

import Login.Pengolahan;
import Login.Setoran;
import Login.StokGudang;
import java.math.BigInteger;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-07T20:06:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(JenisSampah.class)
public class JenisSampah_ { 

    public static volatile CollectionAttribute<JenisSampah, Pengolahan> pengolahanCollection;
    public static volatile CollectionAttribute<JenisSampah, StokGudang> stokGudangCollection;
    public static volatile SingularAttribute<JenisSampah, Integer> idJenissampah;
    public static volatile CollectionAttribute<JenisSampah, Setoran> setoranCollection;
    public static volatile SingularAttribute<JenisSampah, String> namaSampah;
    public static volatile SingularAttribute<JenisSampah, String> kategori;
    public static volatile SingularAttribute<JenisSampah, BigInteger> hargaPerKg;

}