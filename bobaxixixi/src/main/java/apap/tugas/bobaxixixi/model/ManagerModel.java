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
import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name="MANAGER")

public class ManagerModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idManager; //long or bigint ya

    @NotNull
    @Size(max=255)
    @Column(name = "full_name", nullable = false)
    private String namaManager;

    @NotNull
    @Column(name = "gender", nullable = false)
    private Integer genderManager;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="date_of_birth", nullable = false)
    private Date tanggalLahirManager;

    // Relasi dengan Store
    @OneToOne(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private StoreModel store;
}
