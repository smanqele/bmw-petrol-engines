package com.bmw.engine.data.petrolengines.repository.dbmodel;

import com.bmw.engine.data.petrolengines.model.EngineData;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name="BMW_PETROL_ENGINE")
public class PetrolEngine  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="LAYOUT")
    private String layout;

    @Column(name="ENGINE_CODE")
    private String engineCode;

    @Column(name="ENGINE_YEARS")
    private String engineYears;

    @Column(name="DISPLACEMENT")
    private String displacement;

    public enum Layout {
        STRAIGHT_THREE,
        INLINE_FOUR,
        STRAIGHT_SIX,
        V8,
        V10,
        V12
    }

    public static Collection<EngineData> transform(Collection<PetrolEngine> engines){
        Collection<EngineData> data = new ArrayList<>();
        if (engines == null || engines.isEmpty()){
            return data;
        }
        engines.forEach(e -> data.add(transform(e)));
        return data;
    }

    public static EngineData transform(PetrolEngine engine){
        EngineData data = new EngineData();
        if (engine == null){
            return data;
        }
        data.setDisplacement(engine.getDisplacement());
        data.setEngineCode(engine.getEngineCode());
        data.setEngineYears(engine.getEngineYears());
        data.setLayout(retriveLayout(engine.getLayout()));
        return data;
    }

    private static Layout retriveLayout(String layout) {
        if (layout == null || layout.isEmpty()){
            throw new IllegalStateException("Layout cannot be empty");
        }
        return Enum.valueOf(Layout.class, layout);
    }

}
