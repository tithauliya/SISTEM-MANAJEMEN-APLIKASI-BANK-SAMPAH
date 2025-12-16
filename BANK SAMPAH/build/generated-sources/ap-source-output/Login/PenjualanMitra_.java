package Login;

import Login.MitraJual;
import Login.Pengolahan;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-07T20:06:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(PenjualanMitra.class)
public class PenjualanMitra_ { 

    public static volatile SingularAttribute<PenjualanMitra, BigInteger> totalHargaPenjualanMitra;
    public static volatile SingularAttribute<PenjualanMitra, BigInteger> beratKgPenjualanMitra;
    public static volatile SingularAttribute<PenjualanMitra, Integer> idPenjualanMitra;
    public static volatile SingularAttribute<PenjualanMitra, Pengolahan> idPengolahan;
    public static volatile SingularAttribute<PenjualanMitra, Date> tanggalPenjualanMitra;
    public static volatile SingularAttribute<PenjualanMitra, MitraJual> idMitra;

}