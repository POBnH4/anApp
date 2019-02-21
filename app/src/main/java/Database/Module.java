package Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "module")
public class Module {
    @NonNull
    @PrimaryKey(autoGenerate = true)

    private int uid;

    private String reference;

    @ColumnInfo(name = "SCQF_Credits")
    private int scqfCredits;

    private Date createdOn;

    @NonNull
    public int getUid() {
        return uid;
    }

    public void setUid(@NonNull int uid) {
        this.uid = uid;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getScqfCredits() {
        return scqfCredits;
    }

    public void setScqfCredits(int scqfCredits) {
        this.scqfCredits = scqfCredits;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }


}
