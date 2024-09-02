package gep.ma.intelligent.pv.Models;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("plants")
public class Plants {

    @PrimaryKey
    private PlantsKey key;

    @Column("currentpower")
    private Double currentPower;

    @Column("dayenergy")
    private Double dayEnergy;

    @Column("inverternum")
    private Integer inverterNum;

    @Column("performanceratio")
    private Double performanceRatio;

    @Column("plantname")
    private String plantName;

    @Column("specificenergy")
    private Double specificEnergy;

    @Column("totalenergy")
    private Double totalEnergy;

    @Column("totalradiation")
    private Double totalRadiation;

    @Column("totalrate")
    private Double totalRate;

    @Column("totalstringpower")
    private Double totalStringPower;
}
