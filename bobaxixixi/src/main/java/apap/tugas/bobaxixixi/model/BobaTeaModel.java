package apap.tugas.bobaxixixi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name="BOBA_TEA")

public class BobaTeaModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBoba; //long or bigint ya

    @NotNull
    @Size(max=255)
    @Column(name = "name", nullable = false)
    private String namaBoba;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer hargaBoba;

    @NotNull
    @Column(name = "size", nullable = false)
    private Integer sizeBoba;

    @NotNull
    @Column(name = "ice_level", nullable = false)
    private Integer iceLevel;

    @NotNull
    @Column(name = "sugar_level", nullable = false)
    private Integer sugarLevel;

    // Relasi dengan Topping Boba
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_topping", referencedColumnName = "idTopping", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToppingModel topping;

    // Relasi dengan StoreBobaTea
    @OneToMany(mappedBy = "bobaTea", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StoreBobaTeaModel> listStoreBobaTea;
}

