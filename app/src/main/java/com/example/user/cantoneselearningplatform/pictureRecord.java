package com.example.user.cantoneselearningplatform;

import java.sql.Blob;
import java.sql.Timestamp;

/**
 * Created by user on 26/3/16.
 */
public class pictureRecord {
    Integer p_id;
    Integer c_id;
    byte[] picture;
    Timestamp ts;
    public pictureRecord(Integer p_id1, Integer c_id1,byte[] picture1,Timestamp ts1) {
        this.p_id = p_id1;
        this.c_id = c_id1;
        this.picture = picture1;
        this.ts = ts1;
    }
    public Integer getP_id(){
        return this.p_id;
    }
    public Integer getC_id(){
        return this.c_id;
    }
    public byte[] getPicture(){
        return this.picture;
    }
    public Timestamp getTs(){
        return this.ts;
    }
}
