package apap.tugas.bobaxixixi.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name="TOPPING")

public class ToppingModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTopping; //long or bigint ya

    @NotNull
    @Size(max=255)
    @Column(name = "name", nullable = false)
    private String namaTopping;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer hargaTopping;

    // Relasi dengan Boba Tea
    @OneToMany(mappedBy = "topping", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BobaTeaModel> listBobaTea;
}
