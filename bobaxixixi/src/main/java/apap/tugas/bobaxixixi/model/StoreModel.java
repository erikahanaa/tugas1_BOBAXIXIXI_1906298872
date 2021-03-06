package apap.tugas.bobaxixixi.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name="STORE")

public class StoreModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStore; //long or bigint ya

    @NotNull
    @Size(max=255)
    @Column(name = "name", nullable = false)
    private String namaStore;

    @NotNull
    @Size(max=255)
    @Column(name = "address", nullable = false)
    private String alamatStore;

    @NotNull
    @Size(max=10)
    @Column(name = "store_code", nullable = false, unique = true)
    private String storeCode;

    @NotNull
    @Column(name = "open_hour", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openHour;

    @NotNull
    @Column(name = "close_hour", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeHour;

    // Relasi dengan Manager
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_manager", referencedColumnName = "idManager", nullable = false, unique = true)
    private ManagerModel manager;

    // Relasi dengan StoreBobaModel
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StoreBobaTeaModel> listStoreBobaTea;
}
