package th.mi.tdc.quiz.entity;

import java.time.Instant;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity(name = "refreshtoken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Nst user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

}
