package pl.mroz.buddiesapi.infrastructure.database.rental;

import jakarta.persistence.Basic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mroz.buddiesapi.domain.rental.Rental;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalEntity implements RentalRepository.IRentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID rentalId;

    //    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    //    @JoinColumn(name = "creator_id")
    //    private Account creator;

    @Basic
    //    @NotBlank
    //    @Size(max = 100)
    private String title;

    @Basic
    private boolean isNegotiable;

    @Basic
    //    @NotBlank
    //    @Size(max = 3000)
    private String description;

    @Basic
    private String locationStr;

    @Basic
    private double locationLng;

    @Basic
    private double locationLat;

    //  -----details:-----

    @Basic
    private int price;

    @Basic
    private int deposit;

    @Basic
    private int rooms;

    @Basic
    private int floor;

    @Basic
    private double size;

    @Basic
    @Column(name = "price_m_sq")
    private double pricePerM;

    @Basic
    private int buildYear;

    @Basic
    private LocalDate rentDate;

    @ElementCollection
    @CollectionTable(name = "feature_tags", joinColumns = @JoinColumn(name = "rental_id"))
    private Set<String> featureTags;

    @ElementCollection
    @CollectionTable(name = "rental_pic_urls", joinColumns = @JoinColumn(name = "rental_id"))
    private Set<String> photoUrls;

    // -----------------------

    //    @JsonIgnore
    //    @ManyToMany(fetch = FetchType.EAGER,
    //            mappedBy = "favoriteRentals"
    //    )
    //    private Set<Account> accountFavoriteSet = new HashSet<>();

    static RentalEntity fromDomain(Rental rental) {
        return RentalEntity.builder()
                //.creator(accountService.getById(rental.getAccountId()))
                .title(rental.getTitle())
                .isNegotiable(rental.isNegotiable())
                .description(rental.getDescription())
                .locationStr(rental.getLocationStr())
                .locationLat(rental.getLocationLat())
                .locationLng(rental.getLocationLng())
                .price(rental.getPrice())
                .deposit(rental.getDeposit())
                .rooms(rental.getRooms())
                .floor(rental.getFloor())
                .size(rental.getSize())
                .pricePerM(rental.getPricePerM())
                .buildYear(rental.getBuildYear())
                .rentDate(rental.getRentDate())
                .featureTags(rental.getFeatureTags())
                .photoUrls(rental.getPhotoUrls())
                .build();

    }
}
