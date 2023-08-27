package com.distribuida.db;

import jakarta.persistence.*;

@Entity
@Table(name = "singerinstrument")
public class SingerInstrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "singer_id")
    private Integer singer_id;

    @Column(name = "instrument_id")
    private Integer instrument_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(Integer singer_id) {
        this.singer_id = singer_id;
    }

    public Integer getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(Integer instrument_id) {
        this.instrument_id = instrument_id;
    }

    @Override
    public String toString() {
        return "SingerInstrument{" +
                "id=" + id +
                ", singer_id=" + singer_id +
                ", instrument_id=" + instrument_id +
                '}';
    }
}
