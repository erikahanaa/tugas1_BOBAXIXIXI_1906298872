package apap.tugas.bobaxixixi.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name="STORE_BOBA_TEA") // not sure tho

public class StoreBobaTeaModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStoreBoba; //long or bigint ya

    @NotNull
    @Size(max=12)
    @Column(name = "production_code", nullable = false, unique = true)
    private String productionCode;

    // Relasi dengan Store
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_store", referencedColumnName = "idStore", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StoreModel store;

    // Relasi dengan Boba Tea
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_boba", referencedColumnName = "idBoba", nullable = false) // ini masih salah
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BobaTeaModel bobaTea;
}
